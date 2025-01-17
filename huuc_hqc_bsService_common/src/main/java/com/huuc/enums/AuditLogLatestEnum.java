package com.huuc.enums;

import lombok.Getter;

@Getter
public enum AuditLogLatestEnum {
    AUDIT_LOG_LATEST_YES(1, "是"),
    AUDIT_LOG_LATEST_NO(2, "否");

    private final int code;
    private final String msg;

    AuditLogLatestEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
