package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页参数", description = "分页参数")
public class PageDTO {
    @ApiModelProperty("当前页码")
    private Integer pageNumber;

    @ApiModelProperty("每页显示数量")
    private Integer pageSize;
}
