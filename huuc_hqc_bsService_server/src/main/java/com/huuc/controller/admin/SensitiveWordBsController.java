package com.huuc.controller.admin;

import com.huuc.SensitiveWordBsConfig.WordAllow;
import com.huuc.SensitiveWordBsConfig.WordDeny;
import com.huuc.SensitiveWordBsConfig.WordVO;
import com.huuc.result.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@Slf4j
@Api(tags = "违禁词管理相关接口")
public class SensitiveWordBsController {

    private final WordAllow wordAllow;
    private final WordDeny wordDeny;


    @ApiOperation(value = "获取违禁词")
    @GetMapping("/getProhibitedWords")
    public AjaxResult getProhibitedWords() {
        return AjaxResult.success(WordVO.builder().whiteList(wordAllow.list()).blackList(wordDeny.list()).build());
    }
}
