package com.huuc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("sensitive_word_deny")
@ApiModel(value = "敏感词黑名单",description = "敏感词黑名单")
public class SensitiveWordDeny {
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("黑名单词语")
    private String sensitiveWord;

    public SensitiveWordDeny(String sensitiveWord) {
        this.sensitiveWord = sensitiveWord;
    }
}
