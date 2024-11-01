package com.huuc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching//开启缓存注解功能
public class HuucHqcBsServiceServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HuucHqcBsServiceServerApplication.class, args);
    }

}
