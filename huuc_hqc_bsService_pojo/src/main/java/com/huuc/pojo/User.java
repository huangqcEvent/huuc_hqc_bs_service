package com.huuc.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
@Builder
@ApiModel("用户实体类")
public class User {
    //雪花算法
    @ApiModelProperty("用户ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("用户名")
    @TableField("userName")
    private String userName;

    @ApiModelProperty("用户密码")
    @TableField("pWord")
    private String passWord;

    @ApiModelProperty("用户身份")
    @TableField("identity")
    private int identity;

    @ApiModelProperty("用户头像路径")
    @TableField("head")
    private String head;

    @ApiModelProperty("注册时间")
    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("账号 0正常 1停用")
    @TableField("state")
    private int state;
}
