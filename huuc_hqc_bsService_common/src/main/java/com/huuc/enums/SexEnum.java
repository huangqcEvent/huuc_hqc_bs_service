package com.huuc.enums;

import lombok.Getter;

@Getter
public enum SexEnum implements BaseEnum{
    MAN(1,"男"),
    WOMAN(2,"女");

    private final Integer code;
    private final String msg;

    SexEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
