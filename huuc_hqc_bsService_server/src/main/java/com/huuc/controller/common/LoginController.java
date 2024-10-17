package com.huuc.controller.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huuc.context.BaseContext;
import com.huuc.dto.LoginDTO;
import com.huuc.dto.RegisterDTO;
import com.huuc.enums.ConstantEnum;
import com.huuc.enums.UserStateEnum;
import com.huuc.exception.AccountNotFoundException;
import com.huuc.exception.BaseException;
import com.huuc.exception.PasswordErrorException;
import com.huuc.pojo.User;
import com.huuc.properties.JwtProperties;
import com.huuc.result.AjaxResult;
import com.huuc.service.UserService;
import com.huuc.utils.JwtUtils;
import com.huuc.utils.MD5Utils;
import com.huuc.utils.StringUtils;
import com.huuc.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/common")
@AllArgsConstructor
@Slf4j
@Api(tags = "管理员登录相关接口")
public class LoginController{
    private final JwtProperties jwtProperties;
    private final UserService userService;
    @ApiOperation(value = "管理员登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginDTO loginDTO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,loginDTO.getUsername());
        User user = userService.getOne(queryWrapper);
        // 检查 user 是否为 null
        if (user == null) {
            throw new AccountNotFoundException("用户不存在,请注册后登录");
        }
        // 检查账号状态
        if(user.getState() == UserStateEnum.USER_STATE_DOWN.getCode()){
            //todo 获取最新封禁记录
            throw new BaseException("账号已停用"+"\n停用时间:");
        }
        if(loginDTO.getUsername().equals(user.getUserName()) && MD5Utils.generateMD5(loginDTO.getPassword()).equals(user.getPassWord())){
            Map<String, Object> map = new HashMap<>();
            map.put("userId",user.getId());
            String jwt = JwtUtils.createJwt(jwtProperties.getAdminKey(),jwtProperties.getTtlMillis(), map);
            return AjaxResult.success("登陆成功", LoginVO.builder()
                    .jwt(jwt)
                    .id(user.getId())
                    .username(user.getUserName())
                    .head(user.getHead())
                    .identity(user.getIdentity())
                    .build());
        }
        throw new PasswordErrorException("密码错误");
    }

    @ApiOperation(value = "普通用户注册")
    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterDTO registerDTO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,registerDTO.getUsername());
        User user = userService.getOne(queryWrapper);
        if(user!=null){
            throw new AccountNotFoundException("用户已存在,请登录");
        }
        if (StringUtils.isNotEmpty(registerDTO.getUsername()) && StringUtils.isNotEmpty(registerDTO.getPassword())){
            userService.save(User.builder()
                    .userName(registerDTO.getUsername())
                    .passWord(MD5Utils.generateMD5(registerDTO.getPassword()))
                    .identity(ConstantEnum.USER_IS_NOT_ADMIN.getCode())
                    .head(registerDTO.getHead())
                    .state(UserStateEnum.USER_STATE_OK.getCode())
                    .build());
            return AjaxResult.success("注册成功");
        }
        throw new PasswordErrorException("用户名或密码不能为空");
    }

    @ApiOperation(value = "管理员退出")
    @GetMapping("/logout")
    public AjaxResult logout(){
        BaseContext.removeCurrentId();
        return AjaxResult.success("退出成功");
    }
}

