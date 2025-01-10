package com.huuc.SensitiveWordBsConfig;

import com.github.houbb.sensitive.word.api.IWordAllow;
import com.huuc.pojo.SensitiveWordAllow;
import com.huuc.service.SensitiveWordAllowService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WordAllow implements IWordAllow {
    @Resource
    private SensitiveWordAllowService sensitiveWordAllowService;

    @Override
    public List<String> allow() {
        // 显式指定泛型类型，确保类型匹配
        List<SensitiveWordAllow> list = sensitiveWordAllowService.list();
        return list.stream().map(SensitiveWordAllow::getSensitiveWord).collect(Collectors.toList());
    }

    public List<SensitiveWordAllow> list() {
        return sensitiveWordAllowService.list();
    }

    public boolean deleteWhiteById(String id) {
        return sensitiveWordAllowService.removeById(id);
    }

    public boolean save(SensitiveWordAllow sensitiveWordAllow) {
        return sensitiveWordAllowService.save(sensitiveWordAllow);
    }
}
