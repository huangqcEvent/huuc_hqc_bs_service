package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "根据id更改状态入参")
public class UpdateStatusDTO {
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("状态")
    private Integer status;
}
