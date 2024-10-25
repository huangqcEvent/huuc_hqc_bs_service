package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ApiModel("用户身份信息更改分页查询入参")
@Data
public class IdentityPageDTO extends PageDTO{

    @ApiModelProperty("修改时间范围开始")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime downTimeStart;

    @ApiModelProperty("修改时间范围结束")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime downTimeEnd;

    @ApiModelProperty("修改用户名")
    private String userName;

    @ApiModelProperty("修改前身份")
    private String oldIdentity;

    @ApiModelProperty("修改后身份")
    private String newIdentity;
}
