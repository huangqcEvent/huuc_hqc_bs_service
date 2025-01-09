package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户身份信息转化接口入参")
public class UpdateIdentityDTO {
    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("原身份")
    private int oldIdentity;

    @ApiModelProperty("新身份")
    private int newIdentity;

    @ApiModelProperty("更新原因")
    private String updateReason;
}
