package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "人工审核入参")
public class AuditDTO {
    @ApiModelProperty(value = "流水号")
    private String id;

    @ApiModelProperty(value = "审核结果")
    private Integer result;

    @ApiModelProperty(value = "备注")
    private String remark;
}
