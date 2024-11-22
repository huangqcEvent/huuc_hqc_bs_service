package com.huuc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {
    /**
     * 第一组：user
     */
    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户端接口")
                .select()
                //只显示api路径下的页面
                .apis(RequestHandlerSelectors.basePackage("com.huuc.controller"))
                .paths(PathSelectors.regex("/user/.*"))
                .build();
    }
    /**
     * 第二组：admin
     */
    @Bean
    public Docket adminApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("后台接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huuc.controller"))
                .paths(PathSelectors.regex("/admin/.*"))
                .build();
    }
    /**
     * 第三组组：common
     */
    @Bean
    public Docket commonApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("通用接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huuc.controller"))
                .paths(PathSelectors.regex("/common/.*"))
                .build();
    }
}

