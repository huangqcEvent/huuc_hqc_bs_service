package com.huuc.SensitiveWordBsConfig;

import com.github.houbb.sensitive.word.api.IWordAllow;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WordAllow implements IWordAllow {
    @Override
    public List<String> allow() {
        return null;
    }
}
