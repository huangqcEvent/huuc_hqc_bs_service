package com.huuc.enums;

import lombok.Getter;

/**
 * 表白墙贴类型
 */
@Getter
public enum ConfessionTypeEnum implements BaseEnum{
    CONFESSION_TYPE_ONE(1, "找对象"),
    CONFESSION_TYPE_TWO(2, "表白");

    private final Integer code;
    private final String msg;

    ConfessionTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
