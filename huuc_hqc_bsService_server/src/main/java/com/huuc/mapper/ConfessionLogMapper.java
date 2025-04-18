package com.huuc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huuc.dto.ConfessionLogDTO;
import com.huuc.pojo.ConfessionLog;
import com.huuc.service.detail.data.ConfessionInfoData;
import com.huuc.vo.ConfessionDetailVO;
import com.huuc.vo.ConfessionLogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConfessionLogMapper extends BaseMapper<ConfessionLog> {
    /**
     * 表白信息小卡片分页查询
     */
    List<ConfessionLogVO> confessionPageQuery(ConfessionLogDTO confessionLogDTO);

    /**
     * 表白信息详情根据id查询
     */
    ConfessionDetailVO getDetailById(@Param("id") String id, @Param("userId") String userId);

    /**
     * 管理端详情
     */
    ConfessionInfoData getDetailById1(String id);
}
