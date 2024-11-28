package com.huuc.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LostItemPageVO {
    private String id; // 寻物贴id
    private String userId; // 发布者id
    private String userName; // 发布者用户名
    private String head; // 发布者头像
    private String itemName; // 失物名称
    private Integer type; // 类型 1寻物 2寻主
    private String itemDesc; // 失物描述
    private String location; // 丢失地点
    private String phone; // 联系方式（电话）
    private LocalDateTime createTime; // 发布时间
    private String image1; // 图片1
}
