package com.huuc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huuc.dto.ClubRecruitmentNewsDTO;
import com.huuc.pojo.ClubRecruitmentNews;
import com.huuc.result.PageResult;
import com.huuc.vo.ClubRecruitmentNewsVO;

public interface ClubRecruitmentNewsService extends IService<ClubRecruitmentNews> {
    void saveClub(ClubRecruitmentNews clubRecruitmentNews);

    PageResult<ClubRecruitmentNewsVO> getPage(ClubRecruitmentNewsDTO params);
}
