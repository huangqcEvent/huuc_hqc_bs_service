package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("管理端分页查询用户信息入参")
public class UserPageDTO {
    @ApiModelProperty("当前页码")
    private Integer pageNumber;
    @ApiModelProperty("每页显示数量")
    private Integer pageSize;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("账号状态")
    private Integer state;
    @ApiModelProperty("是否是管理员")
    private Integer isAdmin;
    @ApiModelProperty("账号注册起始时间")
    private LocalDateTime startTime;
    @ApiModelProperty("账号注册截止时间")
    private LocalDateTime endTime;
}
