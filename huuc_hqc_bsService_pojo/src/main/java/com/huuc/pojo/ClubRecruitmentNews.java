package com.huuc.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "社团纳新/公告表")
public class ClubRecruitmentNews {
    @ApiModelProperty(value = "社团纳新/公告id", required = true)
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "社团id", required = true)
    private String clubId;

    @ApiModelProperty(value = "标题", required = true)
    private String title;

    @ApiModelProperty(value = "类型 1纳新 2公告", required = true)
    private Integer type;

    @ApiModelProperty(value = "社团工作")
    private String daily;

    @ApiModelProperty(value = "社团福利")
    private String welfare;

    @ApiModelProperty(value = "社团要求/公告内容", required = true)
    private String claim;

    @ApiModelProperty(value = "计划纳新人数")
    private Integer number;

    @ApiModelProperty(value = "已招人数")
    private Integer already;

    @ApiModelProperty(value = "时间类型 1长期 2限时")
    private Integer timeType;

    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "状态 1公开中 2私密 3删除 4待审核 5已招满", required = true)
    private Integer status;

    @ApiModelProperty(value = "创建时间", required = true)
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除时间")
    private LocalDateTime deleteTime;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @ApiModelProperty("图片1")
    private String image1;

    @ApiModelProperty("图片2")
    private String image2;

    @ApiModelProperty("图片3")
    private String image3;
    public ClubRecruitmentNews() {
        // 默认构造函数
    }
}
