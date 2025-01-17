package com.huuc.enums;

import lombok.Getter;


@Getter
public enum AuditTypeEnum {
    AUDIT_TYPE_AUTO(1, "机器审核"),
    AUDIT_TYPE_PEOPLE(2, "人工审核");
    private final int code;
    private final String msg;
    AuditTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
