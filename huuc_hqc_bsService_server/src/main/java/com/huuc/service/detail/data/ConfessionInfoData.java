package com.huuc.service.detail.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "表白信息详情查询")
@Data
public class ConfessionInfoData {
    @ApiModelProperty("表白墙id")
    private String id;

    @ApiModelProperty("发布时间")
    private String createTime;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("概述")
    private String summary;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("类型0找对象1表白")
    private String typeStr;

    @ApiModelProperty("描述，要求")
    private String toUserInfo;

    @ApiModelProperty("图片1")
    private String image1;

    @ApiModelProperty("图片2")
    private String image2;

    @ApiModelProperty("图片3")
    private String image3;
}
