package com.huuc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huuc.pojo.PostAuditLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostAuditLogMapper extends BaseMapper<PostAuditLog> {
    // 可以在这里添加自定义的SQL方法
}
