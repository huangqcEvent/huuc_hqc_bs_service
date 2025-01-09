package com.huuc.SensitiveWordBsConfig;

import com.github.houbb.sensitive.word.api.IWordDeny;
import com.huuc.pojo.SensitiveWordDeny;
import com.huuc.service.SensitiveWordDenyService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WordDeny implements IWordDeny {
    @Resource
    private SensitiveWordDenyService sensitiveWordDenyService;
    @Override
    public List<String> deny() {
        List<SensitiveWordDeny> list = sensitiveWordDenyService.list();
        return list.stream().map(SensitiveWordDeny::getSensitiveWord).collect(Collectors.toList());
    }

    public List<SensitiveWordDeny> list() {
        return sensitiveWordDenyService.list();
    }
}
