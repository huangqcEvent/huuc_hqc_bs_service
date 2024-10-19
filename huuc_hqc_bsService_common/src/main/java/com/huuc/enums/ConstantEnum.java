package com.huuc.enums;

import lombok.Getter;

@Getter
public enum ConstantEnum {
    USER_IS_CLUB(2,"社团"),
    USER_IS_ADMIN(1, "管理员"),
    USER_IS_NOT_ADMIN(0, "普通用户"),
    USER_IS_SUPER_ADMIN(99, "超级管理员");

    private final int code;
    private final String msg;
    ConstantEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

