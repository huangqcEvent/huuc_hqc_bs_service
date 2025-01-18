package com.huuc.vo;

import com.huuc.annotation.GetMsg;
import com.huuc.enums.DownEed;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "封禁信息列表")
public class DownLogVO {
    @ApiModelProperty("封禁id")
    private String id;

    @ApiModelProperty("封禁用户名")
    private String userName;

    @ApiModelProperty("封禁原因")
    private String downReason;

    @ApiModelProperty("封禁时间")
    private String downTime;

    @ApiModelProperty("封禁管理员名称")
    private String downAdminName;

    @ApiModelProperty("是否解封")
    @GetMsg(enumClass = DownEed.class)
    private Integer downEnd;

    @ApiModelProperty("解封时间")
    private String downEndTime;

    @ApiModelProperty("解封管理员名称")
    private String downEndAdminName;
}
