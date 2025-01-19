package com.huuc.event;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.huuc.enums.*;
import com.huuc.exception.ResponseException;
import com.huuc.pojo.*;
import com.huuc.service.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

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
    @Transactional
    public void onApplicationEvent(@NotNull AutoAuditEvent event) {
        try {
            String id = event.getId();
            log.info("收到审核事件，开始审核流水号: {}", id);

            CompletableFuture<PostAudit> postAuditFuture = CompletableFuture.supplyAsync(() -> postAuditService.getById(id));

            PostAudit postAudit = postAuditFuture.get();

            // 先要判断这是不是重新发起的流程
            if (postAudit.getStatus().equals(AuditStatusEnum.AUDIT_STATUS_REJECT.getCode())) {
                // 如果是先更新为待机器审核
                postAudit.setStatus(AuditStatusEnum.AUDIT_STATUS_AUTO.getCode());
                CompletableFuture.runAsync(() -> postAuditService.updateById(postAudit)).get();
            }

            if (!Objects.equals(postAudit.getStatus(), AuditStatusEnum.AUDIT_STATUS_AUTO.getCode())) {
                throw new ResponseException("不是敏感词审核");
            }

            PostTypeEnum postType = PostTypeEnum.getByCode(postAudit.getPostType());
            if (postType == null) {
                log.error("未知的帖子类型: {}", postAudit.getPostType());
                throw new ResponseException("未知的帖子类型");
            }

            // 重新开始了，然后把之前的审批记录改为不是最新，因为会发起一个新流程
            CompletableFuture.runAsync(() -> postAuditLogService.update(
                    new UpdateWrapper<PostAuditLog>()
                            .eq("audit_id", postAudit.getId())
                            .set("is_latest", AuditLogLatestEnum.AUDIT_LOG_LATEST_NO.getCode())
            )).get();

            handlePostAudit(postAudit, postType);
        } catch (Exception e) {
            log.error("自动审核过程中发生错误", e);
            throw new ResponseException("自动审核过程中发生错误");
        }
    }

    private void handlePostAudit(PostAudit postAudit, PostTypeEnum postType) {
        CompletableFuture<Object> postFuture = CompletableFuture.supplyAsync(() -> fetchPost(Long.valueOf(postAudit.getPostId()), postType));
        CompletableFuture<Boolean> isSensitiveFuture = postFuture.thenApply(post -> checkSensitiveContent(post, postType));

        try {
            Object post = postFuture.get();
            boolean isSensitive = isSensitiveFuture.get();

            if (post == null) {
                log.error("未找到对应的帖子: {}", postAudit.getPostId());
                throw new ResponseException("未找到对应的帖子");
            }

            updateAuditResult(postAudit, post, isSensitive, postType);
        } catch (Exception e) {
            log.error("处理帖子过程中发生错误", e);
            throw new ResponseException("处理帖子过程中发生错误");
        }
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
        // 如果不通过，修改审核表为已打回
        postAudit.setStatus(AuditStatusEnum.AUDIT_STATUS_REJECT.getCode());
        CompletableFuture.runAsync(() -> postAuditService.updateById(postAudit));

        // 机器审核不通过插入一条审批记录
        CompletableFuture.runAsync(() -> postAuditLogService.save(
                PostAuditLog.builder()
                        .auditId(postAudit.getId())
                        .auditType(AuditTypeEnum.AUDIT_TYPE_AUTO.getCode())
                        .auditResult(AuditResultEnum.AUDIT_STATUS_REJECT.getCode())
                        .auditTime(LocalDateTime.now())
                        .isLatest(AuditLogLatestEnum.AUDIT_LOG_LATEST_YES.getCode())
                        .auditRemark("内容包含敏感词")
                        .build()
        ));

        // 更新具体帖子的状态为待修改
        switch (postType) {
            case POST_TYPE_CONFESSION:
                ((ConfessionLog) post).setStatus(PostStatusEnum.POST_STATUS_ERROR.getCode());
                CompletableFuture.runAsync(() -> confessionLogService.updateById((ConfessionLog) post));
                break;
            case POST_TYPE_ITEM:
                ((LostItem) post).setStatus(PostStatusEnum.POST_STATUS_ERROR.getCode());
                CompletableFuture.runAsync(() -> lostItemService.updateById((LostItem) post));
                break;
            case POST_TYPE_CLUB:
                ((ClubRecruitmentNews) post).setStatus(PostStatusEnum.POST_STATUS_ERROR.getCode());
                CompletableFuture.runAsync(() -> clubRecruitmentNewsService.updateById((ClubRecruitmentNews) post));
                break;
        }
    }

    private void pass(PostAudit postAudit) {
        // 如果通过，修改审核表为待人工审核
        postAudit.setStatus(AuditStatusEnum.AUDIT_STATUS_PENDING.getCode());
        CompletableFuture.runAsync(() -> postAuditService.updateById(postAudit));

        // 保存一条审核记录
        CompletableFuture.runAsync(() -> postAuditLogService.save(
                PostAuditLog.builder()
                        .auditId(postAudit.getId())
                        .auditType(AuditTypeEnum.AUDIT_TYPE_AUTO.getCode())
                        .auditResult(AuditResultEnum.AUDIT_STATUS_PASS.getCode())
                        .auditTime(LocalDateTime.now())
                        .isLatest(AuditLogLatestEnum.AUDIT_LOG_LATEST_YES.getCode())
                        .auditRemark("机器审核通过")
                        .build()
        ));
    }
}