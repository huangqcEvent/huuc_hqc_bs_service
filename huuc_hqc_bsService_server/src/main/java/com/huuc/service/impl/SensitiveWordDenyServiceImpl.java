package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huuc.mapper.SensitiveWordDenyMapper;
import com.huuc.pojo.SensitiveWordDeny;
import com.huuc.service.SensitiveWordDenyService;
import org.springframework.stereotype.Service;

@Service
public class SensitiveWordDenyServiceImpl extends ServiceImpl<SensitiveWordDenyMapper, SensitiveWordDeny> implements SensitiveWordDenyService {
    // 可以在这里实现自定义业务方法
}
