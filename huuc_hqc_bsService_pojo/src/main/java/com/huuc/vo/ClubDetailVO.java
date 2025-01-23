package com.huuc.vo;

import com.huuc.annotation.GetMsg;
import com.huuc.enums.ClubTypeEnum;
import com.huuc.enums.TimeTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "社团纳新/公告表详情")
public class ClubDetailVO {

    @ApiModelProperty(value = "社团纳新/公告id")
    private String id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "社团id")
    private String clubId;

    @ApiModelProperty(value = "社团名称")
    private String clubName;

    @ApiModelProperty(value = "社团头像")
    private String clubHead;

    @ApiModelProperty(value = "类型 1纳新 2公告")
    @GetMsg(enumClass = ClubTypeEnum.class)
    private Integer type;

    @ApiModelProperty(value = "社团工作")
    private String daily;

    @ApiModelProperty(value = "社团福利")
    private String welfare;

    @ApiModelProperty(value = "社团要求/公告内容")
    private String claim;

    @ApiModelProperty(value = "计划纳新人数")
    private Integer number;

    @ApiModelProperty(value = "已招人数")
    private Integer already;

    @ApiModelProperty(value = "时间类型 1长期 2限时")
    @GetMsg(enumClass = TimeTypeEnum.class)
    private Integer timeType;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("图片1")
    private String image1;

    @ApiModelProperty("图片2")
    private String image2;

    @ApiModelProperty("图片3")
    private String image3;
}
