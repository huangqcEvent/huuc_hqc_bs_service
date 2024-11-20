package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huuc.mapper.SensitiveWordAllowMapper;
import com.huuc.pojo.SensitiveWordAllow;
import com.huuc.service.SensitiveWordAllowService;
import org.springframework.stereotype.Service;

@Service
public class SensitiveWordAllowServiceImpl extends ServiceImpl<SensitiveWordAllowMapper, SensitiveWordAllow> implements SensitiveWordAllowService {
    // 可以在这里实现自定义业务方法
}
