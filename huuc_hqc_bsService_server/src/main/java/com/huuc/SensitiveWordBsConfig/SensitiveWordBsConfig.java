package com.huuc.SensitiveWordBsConfig;


import com.github.houbb.sensitive.word.api.IWordAllow;
import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import com.github.houbb.sensitive.word.support.ignore.SensitiveWordCharIgnores;
import com.github.houbb.sensitive.word.support.resultcondition.WordResultConditions;
import com.github.houbb.sensitive.word.support.tag.WordTags;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@Slf4j
public class SensitiveWordBsConfig {
    @Resource
    private WordAllow wordAllow;
    @Resource
    private WordDeny wordDeny;
    @Bean
    public SensitiveWordBs sensitiveWordBs(){
        //黑白名单使用 默认黑白名单加上自定义的黑白名单
        IWordDeny wordDenys = WordDenys.chains(WordDenys.defaults(), wordDeny);
        IWordAllow wordAllows = WordAllows.chains(WordAllows.defaults(), wordAllow);
        log.info("敏感词过滤");
        return SensitiveWordBs.newInstance()
                .ignoreCase(true)//忽略大小写
                .ignoreWidth(true)//忽略半角圆角
                .ignoreNumStyle(true)//忽略数字的写法
                .ignoreChineseStyle(true)//忽略中文的书写格式
                .ignoreEnglishStyle(true)//忽略英文的书写格式
                .ignoreRepeat(false)//忽略重复字符
                .enableNumCheck(false)//是否启用数字检测。
                .enableEmailCheck(false)//是有启用邮箱检测
                .enableUrlCheck(false)//是否启用链接检测
                .enableIpv4Check(false)//是否启用IPv4检测
                .enableWordCheck(true)//是否启用敏感单词检测
                .numCheckLen(8)//数字检测，自定义指定长度。
                .wordTag(WordTags.none())//词对应的标签
                .wordAllow(wordAllows)
                .wordDeny(wordDenys)
                .charIgnore(SensitiveWordCharIgnores.defaults())//忽略的字符
                .wordResultCondition(WordResultConditions.alwaysTrue())//针对匹配的敏感词额外加工，比如可以限制英文单词必须全匹配
                .init();
    }
}
