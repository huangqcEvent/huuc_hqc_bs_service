package com.huuc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huuc.pojo.LostItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LostItemMapper extends BaseMapper<LostItem> {
    // 你可以在这里添加自定义的查询方法
}
