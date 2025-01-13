package com.huuc.service;

import com.aliyun.oss.common.parser.ResponseParseException;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huuc.pojo.ClubRecruitmentNews;

public interface ClubRecruitmentNewsService extends IService<ClubRecruitmentNews> {
    void saveClub(ClubRecruitmentNews clubRecruitmentNews) throws ResponseParseException;
}
