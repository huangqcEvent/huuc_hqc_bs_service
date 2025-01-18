package com.huuc.enums;

import lombok.Getter;

/**
 * 审批记录是否是最新数据
 */
@Getter
public enum AuditLogLatestEnum implements BaseEnum{
    AUDIT_LOG_LATEST_YES(1, "是"),
    AUDIT_LOG_LATEST_NO(2, "否");

    private final Integer code;
    private final String msg;

    AuditLogLatestEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
