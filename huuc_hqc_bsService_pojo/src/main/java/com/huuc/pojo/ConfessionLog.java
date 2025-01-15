package com.huuc.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "表白墙实体")
@TableName("confession_log")
public class ConfessionLog {
    /**
     * (表白,找对象) 记录id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("(表白,找对象) 记录id")
    private String id;

    /**
     * 发帖用户id
     */
    @ApiModelProperty("发帖用户id")
    private String userId;

    /**
     * 类型 0找对象 1表白
     */
    @ApiModelProperty("类型 0找对象 1表白")
    private Integer type;

    /**
     * 是否匿名 0否 1是
     */
    @ApiModelProperty("是否匿名 0否 1是")
    private Integer isAnonymous;

    /**
     * (表白,找对象) 标题
     */
    @ApiModelProperty("(表白,找对象) 标题")
    private String title;

    /**
     * (表白,找对象) 概要
     */
    @ApiModelProperty("(表白,找对象) 概要")
    private String summary;

    /**
     * (表白,找对象) 内容
     */
    @ApiModelProperty("(表白,找对象) 内容")
    private String content;

    /**
     * 被表白用户简短信息(理想型信息)
     */
    @ApiModelProperty("被表白用户简短信息(理想型信息)")
    private String toUserInfo;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 删除时间
     */
    @ApiModelProperty("删除时间")
    private LocalDateTime deleteTime;

    /**
     * 状态 0删除 1正常 2私密
     */
    @ApiModelProperty("状态 0删除 1正常 2私密")
    private Integer status;

    /**
     * 是否置顶 0否 1是
     */
    @ApiModelProperty("是否置顶 0否 1是")
    private Integer isTop;

    /**
     * 图片1
     */
    @ApiModelProperty("图片1")
    @TableField(value = "image_1")
    private String image1;

    /**
     * 图片2
     */
    @ApiModelProperty("图片2")
    @TableField(value = "image_2")
    private String image2;

    /**
     * 图片3
     */
    @ApiModelProperty("图片3")
    @TableField(value = "image_3")
    private String image3;
}
