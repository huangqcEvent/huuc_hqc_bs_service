package com.huuc.enums;

import lombok.Getter;

@Getter
public enum PostTypeEnum {
    POST_TYPE_CONFESSION(1, "表白墙贴"),
    POST_TYPE_ITEM(2, "寻物贴"),
    POST_TYPE_CLUB(3, "社团贴");

    private final int code;
    private final String msg;

    PostTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static PostTypeEnum getByCode(int code) {
        for (PostTypeEnum postTypeEnum : PostTypeEnum.values()) {
            if (postTypeEnum.getCode() == code) {
                return postTypeEnum;
            }
        }
        return null;
    }
}
