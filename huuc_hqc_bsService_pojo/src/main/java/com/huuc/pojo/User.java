package com.huuc.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.huuc.annotation.GetMsg;
import com.huuc.enums.ConstantEnum;
import com.huuc.enums.UserStateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
@Builder
@ApiModel(value = "用户实体类")
public class User {
    //雪花算法
    @ApiModelProperty("用户ID")
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("用户名")
    @TableField("userName")
    private String userName;

    @ApiModelProperty("用户密码")
    @TableField("pWord")
    private String passWord;

    @ApiModelProperty("用户身份")
    @TableField("identity")
    @GetMsg(enumClass = ConstantEnum.class)
    private Integer identity;

    @ApiModelProperty("用户头像路径")
    @TableField("head")
    private String head;

    @ApiModelProperty("注册时间")
    @TableField("createTime")
    private LocalDateTime createTime;

    @ApiModelProperty("账号 0正常 1停用")
    @TableField("state")
    @GetMsg(enumClass = UserStateEnum.class)
    private Integer state;
}
