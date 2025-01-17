package com.huuc.enums;

import lombok.Getter;

@Getter
public enum AuditResultEnum {
    AUDIT_STATUS_PASS(1, "通过"),
    AUDIT_STATUS_REJECT(2, "不通过");
    private final int code;
    private final String msg;

    AuditResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
