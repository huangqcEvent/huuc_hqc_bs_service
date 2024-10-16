package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huuc.dto.UserPageDTO;
import com.huuc.mapper.UserMapper;
import com.huuc.pojo.User;
import com.huuc.result.PageResult;
import com.huuc.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public PageResult<User> getUserList(UserPageDTO userPageDTO) {
        //1.设置分页
        PageHelper.startPage(userPageDTO.getPageNumber(), userPageDTO.getPageSize());
        //2.执行查询
        Page<User> userPage = (Page<User>) baseMapper.userPage(userPageDTO);
        //3.封装结果
        return new PageResult<>(userPage.getTotal(), userPage.getResult());
    }
}
