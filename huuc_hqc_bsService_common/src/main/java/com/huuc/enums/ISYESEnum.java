package com.huuc.enums;

import lombok.Getter;

/**
 * 是否匿名，是否置顶
 */
@Getter
public enum ISYESEnum implements BaseEnum{
    IS_YES(1, "是"),
    IS_NO(0, "否");

    private final Integer code;
    private final String msg;

    ISYESEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
