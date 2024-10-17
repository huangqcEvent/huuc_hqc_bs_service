package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huuc.mapper.DownLogMapper;
import com.huuc.pojo.DownLog;
import com.huuc.service.DownLogService;
import org.springframework.stereotype.Service;

@Service
public class DownLogServiceImpl extends ServiceImpl<DownLogMapper, DownLog> implements DownLogService {
}
