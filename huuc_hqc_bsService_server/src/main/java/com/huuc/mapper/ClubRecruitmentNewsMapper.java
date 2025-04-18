package com.huuc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huuc.dto.ClubPageDTO;
import com.huuc.pojo.ClubRecruitmentNews;
import com.huuc.service.detail.data.ClubRecruitmentNewsInfoData;
import com.huuc.vo.ClubDetailVO;
import com.huuc.vo.ClubPageVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClubRecruitmentNewsMapper extends BaseMapper<ClubRecruitmentNews> {
    List<ClubPageVO> getPage(ClubPageDTO clubPageDTO);

    ClubRecruitmentNewsInfoData getDetailById1(String postId);

    ClubDetailVO getDetailById(@Param("postId") String postId, @Param("userId") String userId);
}
