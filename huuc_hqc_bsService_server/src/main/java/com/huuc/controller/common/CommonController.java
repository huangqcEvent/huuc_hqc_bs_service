package com.huuc.controller.common;


import com.huuc.result.AjaxResult;
import com.huuc.utils.OssUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@Slf4j
@Api(tags = "通用接口")
@RestController
@RequestMapping("/common")
public class CommonController {
    private final OssUtils ossUtils;

    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public AjaxResult upload(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return AjaxResult.error("文件不能为空");
        }

        // 获得原始文件名, 当两个文件名字相同，会有覆盖的问题出现
        String fileName = file.getOriginalFilename();
        String url = null;
        if (fileName != null) {
            try {
                url = ossUtils.upload(fileName, file.getBytes());
            } catch (IOException e) {
                log.error("文件上传失败", e);
                return AjaxResult.error("文件上传失败");
            }
        }
        return AjaxResult.success(url);
    }
}

