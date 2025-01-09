package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

//寻物贴分页查询入参
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "寻物贴分页查询入参")
public class LostItemPageDTO extends PageDTO{

    @ApiModelProperty("失物名称")
    private String itemName;

    @ApiModelProperty("类型")
    private Integer type; // 类型 1寻物 2寻主

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("发布时间结束")
    private LocalDateTime createTimeStart; // 发布时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("发布时间开始")
    private LocalDateTime createTimeEnd;

    @ApiModelProperty("状态")
    private Integer status;//状态 1正常 2结束 3删除 4私密
}
