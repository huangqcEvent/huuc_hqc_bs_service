package com.huuc.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hqc.jwt")//配置属性类，封装配置文件中的文件项
@Data
public class JwtProperties {

    /**
     * 管理端生成jwt令牌相关配置
     */
    private String adminKey;
    private long ttlMillis;
    private String adminTokenName;

    /**
     * 用户端生成jwt令牌相关配置
     */
    private String userKey;
    private String userTokenName;

}
