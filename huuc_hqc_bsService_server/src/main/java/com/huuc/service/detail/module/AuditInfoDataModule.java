package com.huuc.service.detail.module;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huuc.exception.ResponseException;
import com.huuc.pojo.PostAudit;
import com.huuc.pojo.PostAuditLog;
import com.huuc.service.PostAuditLogService;
import com.huuc.service.PostAuditService;
import com.huuc.service.detail.DetailParams;
import com.huuc.service.detail.GetDetailModule;
import com.huuc.service.detail.data.AuditInfoData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuditInfoDataModule implements GetDetailModule<List<AuditInfoData>> {
    @Resource
    private PostAuditService postAuditService;
    @Resource
    private PostAuditLogService postAuditLogService;

    @Override
    public List<AuditInfoData> getData(DetailParams params) {
        //获取唯一的审批流程
        LambdaQueryWrapper<PostAudit> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostAudit::getPostId,params.getPostId()).eq(PostAudit::getPostType,params.getPostType());
        PostAudit postAudit = postAuditService.getOne(queryWrapper);
        if (postAudit == null){
            throw new ResponseException("审批流程不存在");
        }

        LambdaQueryWrapper<PostAuditLog> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(PostAuditLog::getAuditId,postAudit.getId());

        return postAuditLogService.list(queryWrapper1).stream().map(postAuditLog ->
                AuditInfoData.builder()
                        .auditUserName(postAuditLog.getAuditUser())
                        .auditType(postAuditLog.getAuditType())
                        .auditTime(postAuditLog.getAuditTime())
                        .auditResult(postAuditLog.getAuditResult())
                        .auditReason(postAuditLog.getAuditRemark())
                .build()
        ).collect(Collectors.toList());
    }
}
