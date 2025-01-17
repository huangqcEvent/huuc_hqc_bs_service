package com.huuc.enums;

import lombok.Getter;

@Getter
public enum PostSubTypeEnum implements BaseEnum {
    POST_SUB_TYPE_ONE(1, "找对象/寻物/纳新"),
    POST_SUB_TYPE_TWO(2, "表白/寻主/公告");

    private final Integer code;
    private final String msg;

    PostSubTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
