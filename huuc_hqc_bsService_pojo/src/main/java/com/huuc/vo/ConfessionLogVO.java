package com.huuc.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("表白墙小卡片分页查询")
public class ConfessionLogVO {
    @ApiModelProperty("表白id")
    private String id;
    @ApiModelProperty("发墙用户名称 当匿名时为匿名，当不匿名时为用户名")
    private String userName;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("概要")
    private String summary;
    @ApiModelProperty("发布时间")
    private String createTime;
}
