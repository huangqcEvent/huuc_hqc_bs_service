package com.huuc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "com.huuc")
@EnableCaching//开启缓存注解功能
@EnableAsync//开启异步调用
@MapperScan("com.huuc.mapper")
public class HuucHqcBsServiceServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HuucHqcBsServiceServerApplication.class, args);
    }

}
