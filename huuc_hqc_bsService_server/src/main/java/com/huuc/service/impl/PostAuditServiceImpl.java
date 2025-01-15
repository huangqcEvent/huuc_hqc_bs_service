package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huuc.dto.AuditPageDTO;
import com.huuc.mapper.PostAuditMapper;
import com.huuc.pojo.PostAudit;
import com.huuc.result.PageResult;
import com.huuc.service.PostAuditService;
import com.huuc.vo.AuditPageVO;
import org.springframework.stereotype.Service;

@Service
public class PostAuditServiceImpl extends ServiceImpl<PostAuditMapper, PostAudit> implements PostAuditService {
    @Override
    public PageResult<AuditPageVO> getPage(AuditPageDTO params) {
        //设置分页
        PageHelper.startPage(params.getPageNumber(), params.getPageSize());
        //执行查询
        Page<AuditPageVO> pageResult = (Page<AuditPageVO>) baseMapper.getPage(params);
        //封装结果
        return new PageResult<>(pageResult.getTotal(), pageResult.getResult());
    }
}
