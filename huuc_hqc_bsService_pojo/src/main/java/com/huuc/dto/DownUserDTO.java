package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("封禁用户入参")
@Data
public class DownUserDTO {
    //用户
    private String id;
    //状态
    private Integer state;
    //封禁原因
    private String downReason;
}
