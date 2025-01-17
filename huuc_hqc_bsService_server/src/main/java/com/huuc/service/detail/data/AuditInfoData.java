package com.huuc.service.detail.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditInfoData {
    private String auditUserName;
    private String auditType;
    private String auditTime;
    private String auditStatus;
    private String auditReason;
}
