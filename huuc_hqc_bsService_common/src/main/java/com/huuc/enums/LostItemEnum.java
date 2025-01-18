package com.huuc.enums;

import lombok.Getter;

/**
 * 寻物贴类型
 */
@Getter
public enum LostItemEnum implements BaseEnum{
    LOST_ITEM_ONE(1, "寻物"),
    LOST_ITEM_TWO(2, "寻主");

    private final Integer code;
    private final String msg;

    LostItemEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
