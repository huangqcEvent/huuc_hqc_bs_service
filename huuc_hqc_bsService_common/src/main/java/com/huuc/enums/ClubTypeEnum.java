package com.huuc.enums;

import lombok.Getter;

/**
 * 社团贴类型
 */
@Getter
public enum ClubTypeEnum implements BaseEnum{
    CLUB_TYPE_ONE(1, "纳新"),
    CLUB_TYPE_TWO(2, "公告");

    private final Integer code;
    private final String msg;

    ClubTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
