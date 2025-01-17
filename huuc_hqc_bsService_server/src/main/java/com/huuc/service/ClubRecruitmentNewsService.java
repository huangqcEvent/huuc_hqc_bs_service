package com.huuc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huuc.dto.ClubPageDTO;
import com.huuc.pojo.ClubRecruitmentNews;
import com.huuc.result.PageResult;
import com.huuc.service.detail.data.ClubRecruitmentNewsInfoData;
import com.huuc.vo.ClubPageVO;

public interface ClubRecruitmentNewsService extends IService<ClubRecruitmentNews> {
    void saveClub(ClubRecruitmentNews clubRecruitmentNews);

    PageResult<ClubPageVO> getPage(ClubPageDTO clubPageDTO);

    ClubRecruitmentNewsInfoData getDetailById1(String postId);
}
