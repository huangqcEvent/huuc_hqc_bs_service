package com.huuc.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum PostTypeEnum implements BaseEnum{
    POST_TYPE_CONFESSION(1, "表白墙贴"),
    POST_TYPE_ITEM(2, "寻物贴"),
    POST_TYPE_CLUB(3, "社团贴");

    private final Integer code;
    private final String msg;

    PostTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static PostTypeEnum getByCode(Integer code) {
        for (PostTypeEnum postTypeEnum : PostTypeEnum.values()) {
            if (Objects.equals(postTypeEnum.getCode(), code)) {
                return postTypeEnum;
            }
        }
        return null;
    }
}
