package com.huuc.enums;

import lombok.Getter;

@Getter
public enum UserStateEnum {
    USER_STATE_OK(1, "正常"),
    USER_STATE_DOWN(0, "停用");

    private final int code;
    private final String msg;

    UserStateEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}