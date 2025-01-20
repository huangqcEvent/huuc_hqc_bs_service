package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "社团管理分页入参")
@Data
public class ClubPageDTO extends PageDTO {
    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "社团名称")
    private String clubName;

    @ApiModelProperty(value = "类型 1纳新 2公告")
    private Integer type;

    @ApiModelProperty(value = "状态 1公开中 2私密 3删除 4待审核 5已招满")
    private Integer status;

    @ApiModelProperty(value = "时间类型 1长期 2限时")
    private Integer timeType;

    @ApiModelProperty(value = "创建时间开始")
    private LocalDateTime createTimeStart;

    @ApiModelProperty(value = "创建时间结束")
    private LocalDateTime createTimeEnd;
}
