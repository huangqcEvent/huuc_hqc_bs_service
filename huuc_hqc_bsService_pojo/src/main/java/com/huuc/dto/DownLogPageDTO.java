package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@ApiModel("封禁信息分页查询入参")
@Data
public class DownLogPageDTO {
    @ApiModelProperty("当前页码")
    private Integer pageNumber;

    @ApiModelProperty("每页显示数量")
    private Integer pageSize;

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
