package com.huuc.controller.common;


import com.huuc.exception.PasswordErrorException;
import com.huuc.result.AjaxResult;
import com.huuc.utils.OssUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/common")
@AllArgsConstructor
@Slf4j
@Api(tags = "通用接口")
public class CommonController {
   private final OssUtils ossUtils;
    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file) {
            //获得原始文件名,当两个文件名字相同，会有覆盖的问题出现
            String fileName = file.getOriginalFilename();
            String Url = null;
            if (fileName != null) {
                try {
                    Url = ossUtils.upload(fileName, file.getBytes());
                } catch (IOException e) {
                    throw new PasswordErrorException("文件上传失败");
                }
            }
            return AjaxResult.success(Url);

    }
}
