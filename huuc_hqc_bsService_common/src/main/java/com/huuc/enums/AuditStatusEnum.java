package com.huuc.enums;

import lombok.Getter;

@Getter
public enum AuditStatusEnum {
    AUDIT_STATUS_PENDING(1, "待审核"),
    AUDIT_STATUS_PASS(2, "通过"),
    AUDIT_STATUS_REJECT(3, "待修改");

    private final int code;
    private final String msg;

    AuditStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
