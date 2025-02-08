package com.huuc.enums;

import lombok.Getter;

@Getter
public enum gradeEnum implements BaseEnum{
    ONE(1,"大一"),
    TWO(2,"大二"),
    THREE(3,"大三"),
    FOUR(4,"大四"),
    FIVE(5,"研究生");

    private final Integer code;
    private final String msg;

    gradeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
