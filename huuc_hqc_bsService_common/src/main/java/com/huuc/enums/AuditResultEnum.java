package com.huuc.enums;

import lombok.Getter;

/**
 * 审批结果
 */
@Getter
public enum AuditResultEnum implements BaseEnum {
    AUDIT_STATUS_PASS(1, "通过"),
    AUDIT_STATUS_REJECT(2, "不通过");
    private final Integer code;
    private final String msg;

    AuditResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
