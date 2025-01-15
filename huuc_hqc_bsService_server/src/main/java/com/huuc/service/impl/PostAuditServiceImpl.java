package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huuc.mapper.PostAuditMapper;
import com.huuc.pojo.PostAudit;
import com.huuc.service.PostAuditService;
import org.springframework.stereotype.Service;

@Service
public class PostAuditServiceImpl extends ServiceImpl<PostAuditMapper, PostAudit> implements PostAuditService {
}
