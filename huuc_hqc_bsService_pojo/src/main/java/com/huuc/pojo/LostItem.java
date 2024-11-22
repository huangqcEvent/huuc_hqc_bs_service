package com.huuc.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("lost_item")
public class LostItem {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id; // 寻物贴id
    private String userId; // 发布者id
    private String itemName; // 失物名称
    private Integer type; // 类型 1寻物 2寻主
    private String itemDesc; // 失物描述
    private String location; // 丢失地点
    private String phone; // 联系方式（电话）
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime; // 发布时间
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private LocalDateTime updateTime; // 更新时间
    private LocalDateTime deleteTime; // 删除时间
    private Integer isTop; // 是否置顶 1置顶 0未置顶
    private Integer status; // 状态 1正常 2结束 3删除
    private String image1; // 图片1
    private String image2; // 图片2
    private String image3; // 图片3
}
