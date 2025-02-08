package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.huuc.mapper.ClubResumeMapper;
import com.huuc.pojo.ClubResume;
import com.huuc.service.ClubResumeService;
import org.springframework.stereotype.Service;

@Service
public class ClubResumeServiceImpl extends ServiceImpl<ClubResumeMapper, ClubResume> implements ClubResumeService {
}
