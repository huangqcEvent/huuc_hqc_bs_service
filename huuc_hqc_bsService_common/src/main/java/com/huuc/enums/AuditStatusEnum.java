package com.huuc.enums;

import lombok.Getter;

/**
 * 审核状态
 */
@Getter
public enum AuditStatusEnum implements BaseEnum{
    AUDIT_STATUS_AUTO(1, "自动敏感词审核"),
    AUDIT_STATUS_PENDING(2, "待人工审核"),
    AUDIT_STATUS_PASS(3, "通过"),
    AUDIT_STATUS_REJECT(4, "已打回");

    private final Integer code;
    private final String msg;

    AuditStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
