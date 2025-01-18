package com.huuc.service.detail.data;

import com.huuc.annotation.GetMsg;
import com.huuc.enums.AuditResultEnum;
import com.huuc.enums.AuditTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AuditInfoData {
    private String auditUserName;
    @GetMsg(enumClass = AuditTypeEnum.class)
    private Integer auditType;
    private LocalDateTime auditTime;
    @GetMsg(enumClass = AuditResultEnum.class)
    private Integer auditResult;
    private String auditReason;
}
