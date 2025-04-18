package com.huuc.vo;

import com.huuc.annotation.GetMsg;
import com.huuc.enums.LostItemEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "寻物贴详情返回", description = "寻物贴详情返回")
@Data
public class LostItemDetailVO {
    @ApiModelProperty("寻物贴id")
    private String id; // 寻物贴id

    @ApiModelProperty("发布者id")
    private String userId; // 发布者id

    @ApiModelProperty("发布者用户名")
    private String userName; // 发布者用户名

    @ApiModelProperty("发布者头像")
    private String head; // 发布者头像

    @ApiModelProperty("失物名称")
    private String itemName; // 失物名称

    @ApiModelProperty("类型 1寻物 2寻主")
    @GetMsg(enumClass = LostItemEnum.class)
    private Integer type; // 类型 1寻物 2寻主

    @ApiModelProperty("失物描述")
    private String itemDesc; // 失物描述

    @ApiModelProperty("丢失地点")
    private String location; // 丢失地点

    @ApiModelProperty("联系方式（电话）")
    private String phone; // 联系方式（电话）

    @ApiModelProperty("发布时间")
    private String createTime; // 发布时间

    @ApiModelProperty("图片1")
    private String image1; // 图片1

    @ApiModelProperty("图片2")
    private String image2; // 图片2

    @ApiModelProperty("图片3")
    private String image3; // 图片3

    @ApiModelProperty("是否点赞")
    private Integer isLiked;

    @ApiModelProperty("是否收藏")
    private Integer isFavored;
}
