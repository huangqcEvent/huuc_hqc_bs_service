package com.huuc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huuc.pojo.ClubRecruitmentNews;

public interface ClubRecruitmentNewsService extends IService<ClubRecruitmentNews> {
    void saveClub(ClubRecruitmentNews clubRecruitmentNews);
}
