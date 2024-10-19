package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huuc.mapper.UserIdentityUpdateLogMapper;
import com.huuc.pojo.UserIdentityUpdateLog;
import com.huuc.service.UserIdentityUpdateLogService;
import org.springframework.stereotype.Service;

@Service
public class UserIdentityUpdateLogServiceImpl extends ServiceImpl<UserIdentityUpdateLogMapper, UserIdentityUpdateLog> implements UserIdentityUpdateLogService {
}
