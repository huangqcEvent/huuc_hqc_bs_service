package com.huuc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huuc.dto.ClubRecruitmentNewsDTO;
import com.huuc.pojo.ClubRecruitmentNews;
import com.huuc.vo.ClubRecruitmentNewsVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClubRecruitmentNewsMapper extends BaseMapper<ClubRecruitmentNews> {

    List<ClubRecruitmentNewsVO> getPage(ClubRecruitmentNewsDTO params);
}
