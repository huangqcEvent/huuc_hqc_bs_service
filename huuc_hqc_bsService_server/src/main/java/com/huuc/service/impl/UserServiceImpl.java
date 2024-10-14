package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huuc.pojo.User;
import com.huuc.mapper.UserMapper;
import com.huuc.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
