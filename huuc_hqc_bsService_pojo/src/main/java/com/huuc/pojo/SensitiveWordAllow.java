package com.huuc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("sensitive_word_allow")
@ApiModel(value = "敏感词白名单",description = "敏感词白名单")
public class SensitiveWordAllow {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("白名单词语")
    private String sensitiveWord;
}
