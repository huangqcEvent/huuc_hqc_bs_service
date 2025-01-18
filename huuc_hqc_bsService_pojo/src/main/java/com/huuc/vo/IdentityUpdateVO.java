package com.huuc.vo;

import com.huuc.annotation.GetMsg;
import com.huuc.enums.ConstantEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel(value = "用户身份修改分页查询返回")
@Data
public class IdentityUpdateVO {
    @ApiModelProperty("转化记录id")
    private String id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("修改前身份")
    @GetMsg(enumClass = ConstantEnum.class)
    private Integer oldIdentity;

    @ApiModelProperty("修改后身份")
    @GetMsg(enumClass = ConstantEnum.class)
    private Integer newIdentity;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新原因")
    private String updateReason;

    @ApiModelProperty("操作管理员名称")
    private String updateAdminName;

}
