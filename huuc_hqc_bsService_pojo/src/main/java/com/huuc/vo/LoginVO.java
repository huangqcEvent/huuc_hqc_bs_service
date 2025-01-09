package com.huuc.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel("用户登陆返回实体")
public class LoginVO {
    @ApiModelProperty("用户id")
    private String id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("token")
    private String jwt;

    @ApiModelProperty("头像")
    private String head;

    @ApiModelProperty("是否是管理员")
    private int identity;
}
