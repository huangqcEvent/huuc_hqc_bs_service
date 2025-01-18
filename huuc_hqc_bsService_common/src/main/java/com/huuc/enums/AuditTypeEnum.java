package com.huuc.enums;

import lombok.Getter;

/**
 * 审核类型
 */
@Getter
public enum AuditTypeEnum implements BaseEnum{
    AUDIT_TYPE_AUTO(1, "机器审核"),
    AUDIT_TYPE_PEOPLE(2, "人工审核");
    private final Integer code;
    private final String msg;
    AuditTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
