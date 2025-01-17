package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huuc.context.BaseContext;
import com.huuc.dto.ClubPageDTO;
import com.huuc.enums.PostStatusEnum;
import com.huuc.exception.AccountNotFoundException;
import com.huuc.mapper.ClubRecruitmentNewsMapper;
import com.huuc.pojo.ClubRecruitmentNews;
import com.huuc.result.PageResult;
import com.huuc.service.ClubRecruitmentNewsService;
import com.huuc.vo.ClubPageVO;
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
        clubRecruitmentNews.setStatus(PostStatusEnum.POST_STATUS_AUDIT.getCode());
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

    @Override
    public PageResult<ClubPageVO> getPage(ClubPageDTO clubPageDTO) {
        PageHelper.startPage(clubPageDTO.getPageNumber(), clubPageDTO.getPageSize());

        Page<ClubPageVO> pageResult = (Page<ClubPageVO>) baseMapper.getPage(clubPageDTO);

        return new PageResult<>(pageResult.getTotal(), pageResult.getResult());
    }
}
