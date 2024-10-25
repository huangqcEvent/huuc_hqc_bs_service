package com.huuc.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageDTO {
    @ApiModelProperty("当前页码")
    private Integer pageNumber;

    @ApiModelProperty("每页显示数量")
    private Integer pageSize;
}
