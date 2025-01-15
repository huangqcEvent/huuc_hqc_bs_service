package com.huuc.enums;

import lombok.Getter;

@Getter
public enum AuditStatusEnum {
    AUDIT_STATUS_AUTO(1, "自动敏感词审核"),
    AUDIT_STATUS_PENDING(2, "待审核"),
    AUDIT_STATUS_PASS(3, "通过"),
    AUDIT_STATUS_REJECT(4, "待修改");

    private final int code;
    private final String msg;

    AuditStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
