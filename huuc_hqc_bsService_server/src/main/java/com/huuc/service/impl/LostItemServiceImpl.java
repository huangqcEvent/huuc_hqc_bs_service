package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huuc.mapper.LostItemMapper;
import com.huuc.pojo.LostItem;
import com.huuc.service.LostItemService;
import org.springframework.stereotype.Service;

@Service
public class LostItemServiceImpl extends ServiceImpl<LostItemMapper, LostItem> implements LostItemService {
}
