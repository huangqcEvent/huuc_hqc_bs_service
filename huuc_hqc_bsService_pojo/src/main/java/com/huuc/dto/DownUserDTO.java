package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("封禁用户入参")
@Data
public class DownUserDTO {
    //用户
    @ApiModelProperty("用户id")
    private String id;
    //状态
    @ApiModelProperty("用户状态")
    private Integer state;
    //封禁原因
    @ApiModelProperty("封禁原因")
    private String downReason;
}
