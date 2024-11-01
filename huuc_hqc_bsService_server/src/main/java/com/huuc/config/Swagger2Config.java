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
    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("Eric-SpringBoot整合Knife4j-API文档")
                .description("本文档描述了SpringBoot如何整合Knife4j")
                .version("1.0")
                .contact(new Contact("Eric", "https://blog.csdn.net/weixin_47316183?type=blog", "ericsyn@foxmail.com"))
                .build();
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("Eric-SpringBoot整合Knife4j-API文档")
                .description("本文档描述了SpringBoot如何整合Knife4j")
                .version("1.0")
                .contact(new Contact("Eric", "https://blog.csdn.net/weixin_47316183?type=blog", "ericsyn@foxmail.com"))
                .build();
    }

    /**
     * 第一组：user
     */
    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户端接口")
                .apiInfo(webApiInfo())
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
                .apiInfo(adminApiInfo())
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
                .apiInfo(adminApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huuc.controller"))
                .paths(PathSelectors.regex("/common/.*"))
                .build();
    }
}

