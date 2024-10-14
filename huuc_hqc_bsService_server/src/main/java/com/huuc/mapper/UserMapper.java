package com.huuc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huuc.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
