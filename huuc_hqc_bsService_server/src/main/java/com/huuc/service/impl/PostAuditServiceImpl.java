package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huuc.dto.AuditPageDTO;
import com.huuc.enums.AuditStatusEnum;
import com.huuc.event.AutoAuditEvent;
import com.huuc.mapper.PostAuditMapper;
import com.huuc.pojo.PostAudit;
import com.huuc.result.PageResult;
import com.huuc.service.PostAuditService;
import com.huuc.vo.AuditPageVO;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PostAuditServiceImpl extends ServiceImpl<PostAuditMapper, PostAudit> implements PostAuditService {
    @Resource
    private ApplicationContext applicationContext;

    @Override
    public PageResult<AuditPageVO> getPage(AuditPageDTO params) {
        //设置分页
        PageHelper.startPage(params.getPageNumber(), params.getPageSize());
        //执行查询
        Page<AuditPageVO> pageResult = (Page<AuditPageVO>) baseMapper.getPage(params);
        //封装结果
        return new PageResult<>(pageResult.getTotal(), pageResult.getResult());
    }

    @Override
    public void saveAutoAudit(String postId, Integer postType,Integer releaseType) {
        PostAudit postAudit = PostAudit.builder()
                .postId(postId)
                .releaseType(releaseType)
                .postType(postType)
                .status(AuditStatusEnum.AUDIT_STATUS_AUTO.getCode())
                .build();
        this.save(postAudit);
        applicationContext.publishEvent(new AutoAuditEvent(postAudit.getId()));
    }
}
