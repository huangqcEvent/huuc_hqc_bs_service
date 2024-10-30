package com.huuc.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("表白墙实体")
@TableName("confession_log")
public class ConfessionLog {
    /**
     * (表白,找对象) 记录id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 发帖用户id
     */
    private String userId;

    /**
     * 类型 0找对象 1表白
     */
    private Integer type;

    /**
     * 是否匿名 0否 1是
     */
    private Integer isAnonymous;

    /**
     * (表白,找对象) 标题
     */
    private String title;

    /**
     * (表白,找对象) 概要
     */
    private String summary;

    /**
     * (表白,找对象) 内容
     */
    private String content;

    /**
     * 被表白用户简短信息(理想型信息)
     */
    private String toUserInfo;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 删除时间
     */
    private LocalDateTime deleteTime;

    /**
     * 状态 0删除 1正常 2私密
     */
    private Integer status;

    /**
     * 是否置顶 0否 1是
     */
    private Integer isTop;

    /**
     * 图片1
     */
    @TableField(value = "image_1")
    private String image1;

    /**
     * 图片2
     */
    @TableField(value = "image_2")
    private String image2;

    /**
     * 图片3
     */
    @TableField(value = "image_3")
    private String image3;
}
