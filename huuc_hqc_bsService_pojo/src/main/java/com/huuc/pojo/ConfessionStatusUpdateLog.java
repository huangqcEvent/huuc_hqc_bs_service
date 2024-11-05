package com.huuc.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("表白墙status转换记录实体")
@TableName("confession_status_update_log")
@Builder
public class ConfessionStatusUpdateLog {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("表白墙id")
    private String confessionId;

    @ApiModelProperty("旧状态")
    private Integer oldStatus;

    @ApiModelProperty("新状态")
    private Integer newStatus;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新人id")
    private String updateUserId;

    @ApiModelProperty("更新原因")
    private String updateReason;
}
