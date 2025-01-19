package com.huuc.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huuc.context.BaseContext;
import com.huuc.dto.AuditDTO;
import com.huuc.dto.AuditPageDTO;
import com.huuc.enums.*;
import com.huuc.event.AutoAuditEvent;
import com.huuc.exception.ResponseException;
import com.huuc.mapper.PostAuditMapper;
import com.huuc.pojo.*;
import com.huuc.result.PageResult;
import com.huuc.service.*;
import com.huuc.service.detail.data.UserInfoData;
import com.huuc.vo.AuditPageVO;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class PostAuditServiceImpl extends ServiceImpl<PostAuditMapper, PostAudit> implements PostAuditService {
    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private PostAuditLogService postAuditLogService;
    @Resource
    private UserService userService;
    @Resource
    private LostItemService lostItemService;
    @Resource
    private ClubRecruitmentNewsService clubRecruitmentNewsService;
    @Resource
    private ConfessionLogService confessionLogService;

    @Override
    public PageResult<AuditPageVO> getPage(AuditPageDTO params) {
        //设置分页
        PageHelper.startPage(params.getPageNumber(), params.getPageSize());
        //执行查询
        Page<AuditPageVO> pageResult = (Page<AuditPageVO>) baseMapper.getPage(params);
        //封装结果
        return new PageResult<>(pageResult.getTotal(), pageResult.getResult());
    }

    @Override
    public void saveAutoAudit(String postId, Integer postType,Integer releaseType) {
        PostAudit postAudit = PostAudit.builder()
                .postId(postId)
                .releaseType(releaseType)
                .postType(postType)
                .status(AuditStatusEnum.AUDIT_STATUS_AUTO.getCode())
                .build();
        this.save(postAudit);
        applicationContext.publishEvent(new AutoAuditEvent(postAudit.getId()));
    }

    @Override
    @Transactional
    public void audit(AuditDTO auditDTO) {
        CompletableFuture<PostAudit> postAuditFuture = CompletableFuture.supplyAsync(() -> this.getById(auditDTO.getId()));
        UserInfoData userInfoData = userService.getInfoById(BaseContext.getCurrentId());

        // 首先检测机器审核是否通过
        CompletableFuture<PostAuditLog> postAuditLogFuture = CompletableFuture.supplyAsync(() ->
                postAuditLogService.getOne(Wrappers.<PostAuditLog>lambdaQuery()
                        .eq(PostAuditLog::getAuditId, auditDTO.getId())
                        .eq(PostAuditLog::getIsLatest, ISYESEnum.IS_YES.getCode())
                        .eq(PostAuditLog::getAuditType, AuditStatusEnum.AUDIT_STATUS_AUTO.getCode()))
        );

        // 等待所有异步操作完成
        PostAudit postAudit = postAuditFuture.join();
        PostAuditLog postAuditLog = postAuditLogFuture.join();

        if (postAuditLog == null) {
            throw new ResponseException("请等待机器审核");
        }
        if (Objects.equals(postAuditLog.getAuditResult(), AuditResultEnum.AUDIT_STATUS_REJECT.getCode())) {
            throw new ResponseException("机器审核不通过，无法进行人工审核");
        }

        // 无论审核是否通过，审核记录要保存
        CompletableFuture<Void> saveAuditLogFuture = CompletableFuture.runAsync(() ->
                postAuditLogService.save(
                        PostAuditLog.builder()
                                .auditId(postAudit.getId())
                                .auditType(AuditTypeEnum.AUDIT_TYPE_PEOPLE.getCode())
                                .auditResult(auditDTO.getResult())
                                .auditTime(LocalDateTime.now())
                                .auditUser(userInfoData.getUserName())
                                .isLatest(AuditLogLatestEnum.AUDIT_LOG_LATEST_YES.getCode())
                                .auditRemark(auditDTO.getRemark())
                                .build()
                )
        );

        PostTypeEnum postType = PostTypeEnum.getByCode(postAudit.getPostType());

        // 审核通过
        if (Objects.equals(auditDTO.getResult(), AuditResultEnum.AUDIT_STATUS_PASS.getCode())) {
            // step1 更新审核表状态为通过
            CompletableFuture<Void> updatePostAuditFuture = CompletableFuture.runAsync(() -> {
                postAudit.setStatus(AuditStatusEnum.AUDIT_STATUS_PASS.getCode());
                this.updateById(postAudit);
            });

            // step2 根据提交时选择的公开发布或私密发布进行帖子的更新
            CompletableFuture<Void> updatePostFuture = CompletableFuture.runAsync(() -> {
                if (postType != null) {
                    switch (postType) {
                        case POST_TYPE_CONFESSION:
                            confessionLogService.update(Wrappers.<ConfessionLog>lambdaUpdate()
                                    .eq(ConfessionLog::getId, postAudit.getPostId())
                                    .set(ConfessionLog::getStatus, postAudit.getReleaseType()));
                            break;
                        case POST_TYPE_ITEM:
                            lostItemService.update(Wrappers.<LostItem>lambdaUpdate()
                                    .eq(LostItem::getId, postAudit.getPostId())
                                    .set(LostItem::getStatus, postAudit.getReleaseType()));
                            break;
                        case POST_TYPE_CLUB:
                            clubRecruitmentNewsService.update(Wrappers.<ClubRecruitmentNews>lambdaUpdate()
                                    .eq(ClubRecruitmentNews::getId, postAudit.getPostId())
                                    .set(ClubRecruitmentNews::getStatus, postAudit.getReleaseType()));
                            break;
                        default:
                            log.error("未知的 postType: {}");
                            throw new ResponseException("未知的 postType");
                    }
                }
            });

            // 等待所有异步操作完成
            CompletableFuture.allOf(saveAuditLogFuture, updatePostAuditFuture, updatePostFuture).join();
        } else if (Objects.equals(auditDTO.getResult(), AuditResultEnum.AUDIT_STATUS_REJECT.getCode())) {
            // 更新审核表状态为已打回
            CompletableFuture<Void> updatePostAuditFuture = CompletableFuture.runAsync(() -> {
                postAudit.setStatus(AuditStatusEnum.AUDIT_STATUS_REJECT.getCode());
                this.updateById(postAudit);
            });

            // 更新帖子状态为待修改
            CompletableFuture<Void> updatePostFuture = CompletableFuture.runAsync(() -> {
                if (postType != null) {
                    switch (postType) {
                        case POST_TYPE_CONFESSION:
                            confessionLogService.update(Wrappers.<ConfessionLog>lambdaUpdate()
                                    .eq(ConfessionLog::getId, postAudit.getPostId())
                                    .set(ConfessionLog::getStatus, PostStatusEnum.POST_STATUS_ERROR.getCode()));
                            break;
                        case POST_TYPE_ITEM:
                            lostItemService.update(Wrappers.<LostItem>lambdaUpdate()
                                    .eq(LostItem::getId, postAudit.getPostId())
                                    .set(LostItem::getStatus, PostStatusEnum.POST_STATUS_ERROR.getCode()));
                            break;
                        case POST_TYPE_CLUB:
                            clubRecruitmentNewsService.update(Wrappers.<ClubRecruitmentNews>lambdaUpdate()
                                    .eq(ClubRecruitmentNews::getId, postAudit.getPostId())
                                    .set(ClubRecruitmentNews::getStatus, PostStatusEnum.POST_STATUS_ERROR.getCode()));
                            break;
                        default:
                            log.error("未知的 postType: {}");
                            throw new ResponseException("未知的 postType");
                    }
                }
            });
            // 等待所有异步操作完成
            CompletableFuture.allOf(saveAuditLogFuture, updatePostAuditFuture, updatePostFuture).join();
        }
    }


}
