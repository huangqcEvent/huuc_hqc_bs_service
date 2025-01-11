package com.huuc.controller.user;

import com.huuc.service.ai.BigModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/ai")
@Api(tags = "讯飞大模型接入")
public class BigModelController {

    private final BigModelService bigModelService;

    public BigModelController(BigModelService bigModelService) {
        this.bigModelService = bigModelService;
    }

    @PostMapping("/chat")
    @ApiOperation(value = "提交问题返回讯飞答案")
    public String chat(@RequestParam String question) throws Exception {
        return bigModelService.chat(question);
    }
}
