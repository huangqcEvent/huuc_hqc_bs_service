package com.huuc.SensitiveWordBsConfig;

import com.github.houbb.sensitive.word.api.IWordDeny;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WordDeny implements IWordDeny {
    @Override
    public List<String> deny() {
        List<String> x = new ArrayList<>();
        x.add("测试");
        return x;
    }
}
