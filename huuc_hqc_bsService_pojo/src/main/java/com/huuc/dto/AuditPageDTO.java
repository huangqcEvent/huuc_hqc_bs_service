package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "审核列表入参")
public class AuditPageDTO extends PageDTO {

    @ApiModelProperty(value = "流水号")
    private String id;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "审核状态")
    private Integer status;

    @ApiModelProperty(value = "大类型 1表白墙 2寻物 3社团")
    private Integer postType;

    @ApiModelProperty(value = "小类型")
    private Integer postSubType;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "提交时间开始")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "提交时间结束")
    private LocalDateTime endTime;
}
