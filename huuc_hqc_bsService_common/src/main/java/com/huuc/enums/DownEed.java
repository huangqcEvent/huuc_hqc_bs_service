package com.huuc.enums;

import lombok.Getter;

@Getter
public enum DownEed {
    DOWN_END_TRUE(1, "已解封"),
    DOWN_END_FALSE(0, "未解封");
    private final int code;
    private final String msg;
    DownEed(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
