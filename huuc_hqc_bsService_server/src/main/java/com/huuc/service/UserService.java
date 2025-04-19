package com.huuc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huuc.dto.*;
import com.huuc.pojo.User;
import com.huuc.result.PageResult;
import com.huuc.service.detail.data.UserInfoData;
import com.huuc.vo.DownLogVO;
import com.huuc.vo.AllPostVO;
import com.huuc.vo.IdentityUpdateVO;

import java.util.List;

public interface UserService extends IService<User> {
    /**
     * 员工信息分页查询
     */
    PageResult<User> getUserList(UserPageDTO userPageDTO);

    /**
     * 封禁信息分页查询
     */
    PageResult<DownLogVO> getDownLogList(DownLogPageDTO downLogPageDTO);

    /**
     * 封禁，解封账号
     */
     void updateUserState(DownUserDTO downUserDTO);

    /**
     * 用户身份信息转化
     */
     void updateIdentity(UpdateIdentityDTO updateIdentity);

    /**
     * 账号身份变更信息分页查询
     */
    PageResult<IdentityUpdateVO> getIdentityLog(IdentityPageDTO identityPageDto);

    /**
     * 根据id获取信息
     */
    UserInfoData getInfoById(String userId);

    List<AllPostVO> getAllPost();
}
