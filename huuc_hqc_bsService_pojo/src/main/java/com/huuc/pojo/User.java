package com.huuc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@TableName("user")
@Builder
@ApiModel("用户实体类")
public class User {
    //雪花算法
    @ApiModelProperty("用户ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String Id;

    @ApiModelProperty("用户名")
    @TableField("userName")
    private String userName;

    @ApiModelProperty("用户密码")
    @TableField("pWord")
    private String passWord;

    @ApiModelProperty("是否是管理员 0不是 1是")
    @TableField("isAdmin")
    private int isAdmin;

    @ApiModelProperty("用户头像路径")
    @TableField("head")
    private String head;
}
