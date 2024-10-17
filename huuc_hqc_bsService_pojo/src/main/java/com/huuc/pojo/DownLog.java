package com.huuc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("down_log")
@Data
@ApiModel("封禁记录")
@Builder
public class DownLog {

    @ApiModelProperty("封禁id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("封禁用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("封禁原因")
    @TableField("down_reason")
    private String downReason;

    @ApiModelProperty("封禁时间")
    @TableField("down_time")
    private LocalDateTime downTime;

    @ApiModelProperty("封禁管理员id")
    @TableField("down_admin_id")
    private String downAdminId;

    @ApiModelProperty("是否解封")
    @TableField("down_end")
    private int downEnd;

    @ApiModelProperty("解封时间")
    @TableField("down_end_time")
    private LocalDateTime downEndTime;

    @ApiModelProperty("解封管理员id")
    @TableField("down_end_admin_id")
    private String downEndAdminId;
}
