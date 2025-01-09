package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "封禁信息分页查询入参")
@Data
public class DownLogPageDTO extends PageDTO{

    @ApiModelProperty("封禁时间范围开始")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime downTimeStart;

    @ApiModelProperty("封禁时间范围结束")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime downTimeEnd;

    @ApiModelProperty("封禁用户名")
    private String userName;

    @ApiModelProperty("是否解封")
    private Integer downEnd;
}
