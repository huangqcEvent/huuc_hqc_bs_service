package com.huuc.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("lost_item")
@ApiModel(value = "失物招领表", description = "失物招领表")
public class LostItem {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty("寻物贴id")
    private String id; // 寻物贴id

    @ApiModelProperty("发布者id")
    private String userId; // 发布者id

    @ApiModelProperty("失物名称")
    private String itemName; // 失物名称

    @ApiModelProperty("类型 1寻物 2寻主")
    private Integer type; // 类型 1寻物 2寻主

    @ApiModelProperty("失物描述")
    private String itemDesc; // 失物描述

    @ApiModelProperty("丢失地点")
    private String location; // 丢失地点

    @ApiModelProperty("联系方式（电话）")
    private String phone; // 联系方式（电话）

    @ApiModelProperty("发布时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime; // 发布时间

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private LocalDateTime updateTime; // 更新时间

    @ApiModelProperty("删除时间")
    private LocalDateTime deleteTime; // 删除时间

    @ApiModelProperty("是否置顶 1置顶 0未置顶")
    private Integer isTop; // 是否置顶 1置顶 0未置顶

    @ApiModelProperty("状态 1正常 2结束 3删除 4私密")
    private Integer status; // 状态 1正常 2结束 3删除 4私密

    @ApiModelProperty("图片1")
    private String image1; // 图片1

    @ApiModelProperty("图片2")
    private String image2; // 图片2

    @ApiModelProperty("图片3")
    private String image3; // 图片3
}
