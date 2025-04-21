package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

//寻物贴分页查询入参
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "我的帖子分页查询入参")
public class MyPostPageDTO extends PageDTO{
    @ApiModelProperty("状态")
    private Integer status;
}
