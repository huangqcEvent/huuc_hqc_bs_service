package com.huuc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huuc.dto.DownLogPageDTO;
import com.huuc.dto.IdentityPageDTO;
import com.huuc.dto.UserPageDTO;
import com.huuc.pojo.User;
import com.huuc.service.detail.data.UserInfoData;
import com.huuc.vo.DownLogVO;
import com.huuc.vo.IdentityUpdateVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> userPage(UserPageDTO userPageDTO);

    List<DownLogVO> downLogPage(DownLogPageDTO downLogPageDTO);

    List<IdentityUpdateVO> identityLogPage(IdentityPageDTO identityPageDto);

    UserInfoData getInfoById(String userId);
}
