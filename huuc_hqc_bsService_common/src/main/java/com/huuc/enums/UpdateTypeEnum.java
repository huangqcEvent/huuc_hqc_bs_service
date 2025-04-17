package com.huuc.enums;

import lombok.Getter;

@Getter
public enum UpdateTypeEnum implements BaseEnum {
    IS_LIKED(1,"点赞"),
    IS_FAVORED(2,"收藏"),
    IS_COMMENTED(3,"评论");

    private final Integer code;
    private final String msg;

    UpdateTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
