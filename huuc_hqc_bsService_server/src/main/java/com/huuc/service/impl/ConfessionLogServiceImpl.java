package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huuc.dto.ConfessionLogDTO;
import com.huuc.mapper.ConfessionLogMapper;
import com.huuc.pojo.ConfessionLog;
import com.huuc.result.PageResult;
import com.huuc.service.ConfessionLogService;
import com.huuc.vo.ConfessionDetailVO;
import com.huuc.vo.ConfessionLogVO;
import org.springframework.stereotype.Service;

@Service
public class ConfessionLogServiceImpl extends ServiceImpl<ConfessionLogMapper, ConfessionLog> implements ConfessionLogService {
    @Override
    public PageResult<ConfessionLogVO> confessionPageQuery(ConfessionLogDTO confessionLogDTO) {
        //1.设置分页
        PageHelper.startPage(confessionLogDTO.getPageNumber(), confessionLogDTO.getPageSize());
        //2.执行查询
        Page<ConfessionLogVO> pageResult = (Page<ConfessionLogVO>) baseMapper.confessionPageQuery(confessionLogDTO);
        //3.封装结果
        return new PageResult<>(pageResult.getTotal(), pageResult.getResult());
    }

    @Override
    public ConfessionDetailVO getDetailById(String id) {
        return baseMapper.getDetailById(id);
    }
}
