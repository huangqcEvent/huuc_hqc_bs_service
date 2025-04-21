package com.huuc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huuc.dto.DownLogPageDTO;
import com.huuc.dto.IdentityPageDTO;
import com.huuc.dto.UserPageDTO;
import com.huuc.pojo.User;
import com.huuc.service.detail.data.UserInfoData;
import com.huuc.vo.AllPostVO;
import com.huuc.vo.DownLogVO;
import com.huuc.vo.IdentityUpdateVO;
import com.huuc.vo.MyPostVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> userPage(UserPageDTO userPageDTO);

    List<DownLogVO> downLogPage(DownLogPageDTO downLogPageDTO);

    List<IdentityUpdateVO> identityLogPage(IdentityPageDTO identityPageDto);

    UserInfoData getInfoById(String userId);

    List<AllPostVO> getAllPost(String userId);

    List<MyPostVO> getMyPostPage(@Param("status") Integer status, @Param("userId") String userId);

    List<MyPostVO> getMyLiked(@Param("userId") String userId);

    List<MyPostVO> getMyFavored(@Param("userId") String userId);

    List<MyPostVO> getMyComment(String currentId);
}
