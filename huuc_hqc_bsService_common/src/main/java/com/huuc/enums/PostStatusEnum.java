package com.huuc.enums;

import lombok.Getter;

@Getter
public enum PostStatusEnum {
    POST_STATUS_NORMAL(1, "发布"),
    POST_STATUS_ME(2, "私密"),
    POST_STATUS_DELETE(3, "删除"),
    POST_STATUS_AUDIT(4, "待审核"),
    POST_STATUS_STOP(5, "已完结"),
    POST_STATUS_ERROR(6, "拒审待修改");
    //此处的已完结代表这 表白墙的停止 寻物贴的找到失主 社团贴的已招满

    private final int code;
    private final String msg;

    PostStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
