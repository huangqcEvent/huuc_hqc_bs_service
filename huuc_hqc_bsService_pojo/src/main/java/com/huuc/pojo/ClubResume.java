package com.huuc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("club_resume")
@ApiModel(description = "社团纳新招聘表")
public class ClubResume {

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "简历id")
    private String id;

    @TableField("club_news_id")
    @ApiModelProperty(value = "社团纳新贴id")
    private String clubNewsId;

    @TableField("user_id")
    @ApiModelProperty(value = "投递用户id")
    private String userId;

    @TableField("age")
    @ApiModelProperty(value = "年龄")
    private Integer age;

    @TableField("sex")
    @ApiModelProperty(value = "性别")
    private Integer sex;

    @TableField("name")
    @ApiModelProperty(value = "姓名")
    private String name;

    @TableField("phone")
    @ApiModelProperty(value = "联系方式")
    private String phone;

    @TableField("specialized")
    @ApiModelProperty(value = "专业")
    private String specialized;

    @TableField("grade")
    @ApiModelProperty(value = "年级")
    private Integer grade;

    @TableField("file_address")
    @ApiModelProperty(value = "简历地址")
    private String fileAddress;

    @TableField("create_time")
    @ApiModelProperty(value = "投递时间")
    private LocalDateTime createTime;

    @TableField("status")
    @ApiModelProperty(value = "状态 0待筛选 1待面试 2已录用 3不录用")
    private Integer status;

    @TableField("down_reason")
    @ApiModelProperty(value = "不录用原因")
    private String downReason;
}
