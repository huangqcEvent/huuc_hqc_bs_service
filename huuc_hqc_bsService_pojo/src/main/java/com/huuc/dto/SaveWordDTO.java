package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "新增敏感词入参")
public class SaveWordDTO {
    @ApiModelProperty(value = "敏感词")
    private String word;

    @ApiModelProperty(value = "敏感词类型")
    private Integer type;
}
