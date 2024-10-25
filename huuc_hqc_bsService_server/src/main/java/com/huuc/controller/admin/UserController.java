package com.huuc.controller.admin;

import com.huuc.dto.*;
import com.huuc.pojo.User;
import com.huuc.result.AjaxResult;
import com.huuc.result.PageResult;
import com.huuc.service.UserService;
import com.huuc.vo.DownLogVo;
import com.huuc.vo.IdentityUpdateVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
@AllArgsConstructor
@Slf4j
@Api(tags = "管理员用户管理相关接口")
public class UserController {
    private final UserService userService;
    @ApiOperation(value = "用户信息分页查询")
    @GetMapping("/userPage")
    public AjaxResult getUserList(UserPageDTO userPageDTO) {
        log.info("用户信息分页查询:{}", userPageDTO);
        PageResult<User> pageResult = userService.getUserList(userPageDTO);
        return AjaxResult.success(pageResult);
    }

    @ApiOperation(value = "账号封禁记录分页查询")
    @GetMapping("/downLogPage")
    public AjaxResult getDownLogList(DownLogPageDTO downLogPageDTO) {
        log.info("账号封禁记录分页查询:{}", downLogPageDTO);
        PageResult<DownLogVo> pageResult = userService.getDownLogList(downLogPageDTO);
        return AjaxResult.success(pageResult);
    }

    @ApiOperation(value = "用户身份信息转化分页查询")
    @GetMapping("/identityPage")
    public AjaxResult identityPage(IdentityPageDTO identityPageDto){
        log.info("用户身份信息转化分页查询:{}",identityPageDto);
        PageResult<IdentityUpdateVO> pageResult = userService.getIdentityLog(identityPageDto);
        return AjaxResult.success(pageResult);
    }

    @ApiOperation(value = "封禁账号")
    @PutMapping("/downUser")
    public AjaxResult downUser(@RequestBody DownUserDTO downUserDTO) {
        userService.updateUserState(downUserDTO);
        return AjaxResult.success(downUserDTO.getState() == 0 ? "封禁成功" : "解封成功");
    }

    @ApiOperation(value = "用户身份信息转化")
    @PutMapping("/updateIdentity")
    public AjaxResult updateIdentity(@RequestBody UpdateIdentityDTO updateIdentity){
        if(updateIdentity.getNewIdentity() == updateIdentity.getOldIdentity()){
            return AjaxResult.error("新旧身份不能相同");
        }
        log.info(String.valueOf(updateIdentity));
        userService.updateIdentity(updateIdentity);
        return AjaxResult.success("操作成功");
    }
}
