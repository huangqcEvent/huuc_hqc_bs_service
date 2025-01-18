package com.huuc.enums;

import lombok.Getter;

/**
 * 账号状态
 */
@Getter
public enum UserStateEnum implements BaseEnum {
    USER_STATE_OK(1, "正常"),
    USER_STATE_DOWN(0, "停用");

    private final Integer code;
    private final String msg;

    UserStateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}