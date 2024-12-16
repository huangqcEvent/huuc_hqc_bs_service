package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "更具id更改状态入参")
public class UpdateStatusDTO {
    private String id;
    private Integer status;
}
