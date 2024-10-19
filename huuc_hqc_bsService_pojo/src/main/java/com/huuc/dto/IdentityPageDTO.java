package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@ApiModel("用户身份信息更改分页查询入参")
@Data
public class IdentityPageDTO {
    @ApiModelProperty("当前页码")
    private Integer pageNumber;

    @ApiModelProperty("每页显示数量")
    private Integer pageSize;

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
