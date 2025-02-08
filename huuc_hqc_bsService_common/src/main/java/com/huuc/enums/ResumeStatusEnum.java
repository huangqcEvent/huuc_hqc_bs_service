package com.huuc.enums;

import lombok.Getter;

@Getter
public enum ResumeStatusEnum implements BaseEnum{
    ONE(1,"待筛选"),
    TWO(2,"待面试"),
    THREE(3,"已录用"),
    FOUR(4,"不录用");

    private final Integer code;
    private final String msg;

    ResumeStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
