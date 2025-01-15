package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huuc.context.BaseContext;
import com.huuc.exception.AccountNotFoundException;
import com.huuc.mapper.ClubRecruitmentNewsMapper;
import com.huuc.pojo.ClubRecruitmentNews;
import com.huuc.service.ClubRecruitmentNewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClubRecruitmentNewsServiceImpl extends ServiceImpl<ClubRecruitmentNewsMapper, ClubRecruitmentNews> implements ClubRecruitmentNewsService {
    @Override
    public void saveClub(ClubRecruitmentNews clubRecruitmentNews){
        try{
        //用户id
        clubRecruitmentNews.setClubId(BaseContext.getCurrentId());
        //待审核
        clubRecruitmentNews.setStatus(4);
        //已招人数
        clubRecruitmentNews.setAlready(0);
        //排序
        clubRecruitmentNews.setSort(0);
        this.save(clubRecruitmentNews);
    }catch (Exception e){
            log.error("保存失败",e);
            throw new AccountNotFoundException("保存失败");
        }
    }
}
