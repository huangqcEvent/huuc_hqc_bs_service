package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huuc.context.BaseContext;
import com.huuc.dto.LostItemPageDTO;
import com.huuc.mapper.LostItemMapper;
import com.huuc.pojo.LostItem;
import com.huuc.result.PageResult;
import com.huuc.service.LostItemService;
import com.huuc.service.detail.data.LostItemInfoData;
import com.huuc.vo.LostItemDetailVO;
import com.huuc.vo.LostItemPageVO;
import org.springframework.stereotype.Service;

@Service
public class LostItemServiceImpl extends ServiceImpl<LostItemMapper, LostItem> implements LostItemService {
    @Override
    public PageResult<LostItemPageVO> lostPage(LostItemPageDTO lostItemPageDTO) {
        //设置分页
        PageHelper.startPage(lostItemPageDTO.getPageNumber(), lostItemPageDTO.getPageSize());
        //执行查询
        Page<LostItemPageVO> pageResult = (Page<LostItemPageVO>) baseMapper.lostPage(lostItemPageDTO);
        //封装结果
        return new PageResult<>(pageResult.getTotal(), pageResult.getResult());
    }

    @Override
    public LostItemDetailVO getDetailById(String id) {
        return baseMapper.getDetailById(id, BaseContext.getCurrentId());
    }

    @Override
    public LostItemInfoData getDetailById1(String id) {
        return baseMapper.getDetailById1(id);
    }
}
