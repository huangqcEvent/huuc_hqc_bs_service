package com.huuc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huuc.dto.DownLogPageDTO;
import com.huuc.dto.DownUserDTO;
import com.huuc.dto.UpdateIdentityDTO;
import com.huuc.dto.UserPageDTO;
import com.huuc.pojo.User;
import com.huuc.result.PageResult;
import com.huuc.vo.DownLogVo;

public interface UserService extends IService<User> {
    /**
     * 员工信息分页查询
     */
    PageResult<User> getUserList(UserPageDTO userPageDTO);

    /**
     * 封禁信息分页查询
     */
    PageResult<DownLogVo> getDownLogList(DownLogPageDTO downLogPageDTO);

    /**
     * 封禁，解封账号
     */
     void updateUserState(DownUserDTO downUserDTO);

    /**
     * 用户身份信息转化
     */
     void updateIdentity(UpdateIdentityDTO updateIdentity);
}
