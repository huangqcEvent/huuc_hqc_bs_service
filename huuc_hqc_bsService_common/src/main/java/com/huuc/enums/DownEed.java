package com.huuc.enums;

import lombok.Getter;

/**
 * 封禁记录状态
 */
@Getter
public enum DownEed implements BaseEnum {
    DOWN_END_TRUE(1, "已解封"),
    DOWN_END_FALSE(0, "未解封");
    private final Integer code;
    private final String msg;
    DownEed(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
