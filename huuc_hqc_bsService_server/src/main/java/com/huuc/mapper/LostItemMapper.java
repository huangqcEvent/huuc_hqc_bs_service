package com.huuc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huuc.dto.LostItemPageDTO;
import com.huuc.pojo.LostItem;
import com.huuc.vo.LostItemDetailVO;
import com.huuc.vo.LostItemPageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LostItemMapper extends BaseMapper<LostItem> {
    List<LostItemPageVO> lostPage(LostItemPageDTO lostItemPageDTO);

    LostItemDetailVO getDetailById(String id);
}
