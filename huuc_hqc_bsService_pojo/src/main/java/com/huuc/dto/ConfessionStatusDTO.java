package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("表白墙信息更改入参")
@Data
public class ConfessionStatusDTO {

    @ApiModelProperty("表白墙贴id")
    private String confessionId;

    @ApiModelProperty("旧状态")
    private Integer oldStatus;

    @ApiModelProperty("新状态")
    private Integer newStatus;

    @ApiModelProperty("修改原因")
    private String updateReason;

    @ApiModelProperty("用户id")
    private String userId;
}
