package com.huuc.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CommentsVO implements Serializable {
    @ApiModelProperty("评论id")
    private String id;

    @ApiModelProperty("评论人名称")
    private String userName;

    @ApiModelProperty("评论人头像")
    private String userHead;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("评论时间")
    private LocalDateTime createTime;

    @ApiModelProperty("评论图片")
    private String imageUrl;
}
