package com.huuc.controller.user;

import com.huuc.result.AjaxResult;
import com.huuc.service.UserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/myIndex")
@Slf4j
@Api(tags = "用户首页相关接口")
@AllArgsConstructor
public class MyIndexController {
    private final UserService userService;

    @GetMapping("/getAllPost")
    public AjaxResult getAllPost(){
        return AjaxResult.success(userService.getAllPost());
    }
}
