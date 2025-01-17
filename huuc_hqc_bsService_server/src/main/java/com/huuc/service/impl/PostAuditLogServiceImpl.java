package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huuc.mapper.PostAuditLogMapper;
import com.huuc.pojo.PostAuditLog;
import com.huuc.service.PostAuditLogService;
import org.springframework.stereotype.Service;

@Service
public class PostAuditLogServiceImpl extends ServiceImpl<PostAuditLogMapper, PostAuditLog> implements PostAuditLogService {
    // 可以在这里实现自定义的业务方法
}
