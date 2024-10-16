package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@ApiModel("管理端分页查询用户信息入参")
public class UserPageDTO {
    @ApiModelProperty("当前页码")
    private Integer pageNumber;
    @ApiModelProperty("每页显示数量")
    private Integer pageSize;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("账号状态")
    private Integer state;
    @ApiModelProperty("用户身份")
    private Integer identity;
    @ApiModelProperty("账号注册起始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("账号注册截止时间")
    private LocalDateTime endTime;
}
