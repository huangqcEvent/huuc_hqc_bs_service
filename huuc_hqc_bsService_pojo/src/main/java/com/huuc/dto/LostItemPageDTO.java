package com.huuc.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

//寻物贴分页查询入参
@EqualsAndHashCode(callSuper = true)
@Data
public class LostItemPageDTO extends PageDTO{
    private String itemName;
    private Integer type; // 类型 1寻物 2寻主
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTimeStart; // 发布时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTimeEnd;
}
