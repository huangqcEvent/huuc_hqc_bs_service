package com.huuc.event;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.huuc.enums.*;
import com.huuc.pojo.*;
import com.huuc.service.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Component
public class AutoAuditEventListener implements ApplicationListener<AutoAuditEvent> {
    @Resource
    private PostAuditService postAuditService;
    @Resource
    private ConfessionLogService confessionLogService;
    @Resource
    private LostItemService lostItemService;
    @Resource
    private ClubRecruitmentNewsService clubRecruitmentNewsService;
    @Resource
    private SensitiveWordBs sensitiveWordBs;
    @Resource
    private PostAuditLogService postAuditLogService;

    @Override
    @Async
    public void onApplicationEvent(@NotNull AutoAuditEvent event) {
        try {
            String id = event.getId();
            log.info("收到审核事件，开始审核流水号: {}", id);

            PostAudit postAudit = postAuditService.getById(id);
            if (!Objects.equals(postAudit.getStatus(), AuditStatusEnum.AUDIT_STATUS_AUTO.getCode())) {
                return;
            }

            PostTypeEnum postType = PostTypeEnum.getByCode(postAudit.getPostType());
            if (postType == null) {
                log.warn("未知的帖子类型: {}", postAudit.getPostType());
                return;
            }
            //重新开始了，然后把之前的审批记录改为不是最新，因为会发起一个新流程
            postAuditLogService.update(
                    new UpdateWrapper<PostAuditLog>()
                            .eq("audit_id", postAudit.getId())
                            .set("is_latest", AuditLogLatestEnum.AUDIT_LOG_LATEST_NO.getCode())
            );

            handlePostAudit(postAudit, postType);
        } catch (Exception e) {
            log.error("自动审核过程中发生错误", e);
        }
    }

    private void handlePostAudit(PostAudit postAudit, PostTypeEnum postType) {
        Object post = fetchPost(Long.valueOf(postAudit.getPostId()), postType);
        if (post == null) {
            log.warn("未找到对应的帖子: {}", postAudit.getPostId());
            return;
        }

        boolean isSensitive = checkSensitiveContent(post, postType);
        updateAuditResult(postAudit, post, isSensitive, postType);
    }

    private Object fetchPost(Long postId, PostTypeEnum postType) {
        switch (postType) {
            case POST_TYPE_CONFESSION:
                return confessionLogService.getById(postId);
            case POST_TYPE_ITEM:
                return lostItemService.getById(postId);
            case POST_TYPE_CLUB:
                return clubRecruitmentNewsService.getById(postId);
            default:
                return null;
        }
    }

    private boolean checkSensitiveContent(Object post, PostTypeEnum postType) {
        switch (postType) {
            case POST_TYPE_CONFESSION:
                ConfessionLog confessionLog = (ConfessionLog) post;
                return sensitiveWordBs.contains(confessionLog.getContent())
                        || sensitiveWordBs.contains(confessionLog.getSummary())
                        || sensitiveWordBs.contains(confessionLog.getTitle())
                        || sensitiveWordBs.contains(confessionLog.getToUserInfo());
            case POST_TYPE_ITEM:
                LostItem lostItem = (LostItem) post;
                return sensitiveWordBs.contains(lostItem.getItemDesc())
                        || sensitiveWordBs.contains(lostItem.getItemName())
                        || sensitiveWordBs.contains(lostItem.getLocation());
            case POST_TYPE_CLUB:
                ClubRecruitmentNews clubRecruitmentNews = (ClubRecruitmentNews) post;
                return sensitiveWordBs.contains(clubRecruitmentNews.getTitle())
                        || sensitiveWordBs.contains(clubRecruitmentNews.getClaim())
                        || sensitiveWordBs.contains(clubRecruitmentNews.getDaily())
                        || sensitiveWordBs.contains(clubRecruitmentNews.getWelfare());
            default:
                return false;
        }
    }

    private void updateAuditResult(PostAudit postAudit, Object post, boolean isSensitive, PostTypeEnum postType) {
        if (isSensitive) {
            reject(postAudit, post, postType);
        } else {
            pass(postAudit);
        }
    }

    public void reject(PostAudit postAudit, Object post, PostTypeEnum postType) {
        postAudit.setStatus(AuditStatusEnum.AUDIT_STATUS_REJECT.getCode());
        postAuditService.updateById(postAudit);

//        //一旦出现不通过的记录，就把之前所以的审批记录改为不是最新，因为会发起一个新流程
//        postAuditLogService.update(
//                new UpdateWrapper<PostAuditLog>()
//                        .eq("audit_id", postAudit.getId())
//                        .set("is_latest", AuditLogLatestEnum.AUDIT_LOG_LATEST_NO.getCode())
//        );

        //机器审核不通过插入一条审批记录
        postAuditLogService.save(
                PostAuditLog.builder()
                        .auditId(postAudit.getId())
                        .auditType(AuditTypeEnum.AUDIT_TYPE_AUTO.getCode())
                        .auditResult(AuditResultEnum.AUDIT_STATUS_REJECT.getCode())
                        .auditTime(LocalDateTime.now())
                        .isLatest(AuditLogLatestEnum.AUDIT_LOG_LATEST_YES.getCode())
                        .auditRemark("内容包含敏感词")
                        .build()
        );

        switch (postType) {
            case POST_TYPE_CONFESSION:
                ((ConfessionLog) post).setStatus(PostStatusEnum.POST_STATUS_ERROR.getCode());
                confessionLogService.updateById((ConfessionLog) post);
                break;
            case POST_TYPE_ITEM:
                ((LostItem) post).setStatus(PostStatusEnum.POST_STATUS_ERROR.getCode());
                lostItemService.updateById((LostItem) post);
                break;
            case POST_TYPE_CLUB:
                ((ClubRecruitmentNews) post).setStatus(PostStatusEnum.POST_STATUS_ERROR.getCode());
                clubRecruitmentNewsService.updateById((ClubRecruitmentNews) post);
                break;
        }
    }

    private void pass(PostAudit postAudit) {
        postAudit.setStatus(AuditStatusEnum.AUDIT_STATUS_PENDING.getCode());
        postAuditService.updateById(postAudit);

        postAuditLogService.save(
                PostAuditLog.builder()
                        .auditId(postAudit.getId())
                        .auditType(AuditTypeEnum.AUDIT_TYPE_AUTO.getCode())
                        .auditResult(AuditResultEnum.AUDIT_STATUS_PASS.getCode())
                        .auditTime(LocalDateTime.now())
                        .isLatest(AuditLogLatestEnum.AUDIT_LOG_LATEST_YES.getCode())
                        .auditRemark("机器审核通过")
                        .build()
        );
    }
}
