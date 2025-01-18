package com.huuc.enums;

import lombok.Getter;

@Getter
public enum TimeTypeEnum implements BaseEnum{
    TIME_TYPE_ONE(1, "长期"),
    TIME_TYPE_TWO(2, "限时");

    private final Integer code;
    private final String msg;

    TimeTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
