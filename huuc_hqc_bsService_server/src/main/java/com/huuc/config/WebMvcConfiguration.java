package com.huuc.config;

import com.huuc.interceptor.JwtTokenAdminInterceptor;
import com.huuc.json.JacksonObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * 配置类，注册web层相关组件
 */
@Configuration
@Slf4j
@AllArgsConstructor
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    private final JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    /**
     * 注册自定义拦截器
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")//拦截路径
                .addPathPatterns("/common/**")
                .addPathPatterns("/user/**")
                .addPathPatterns("/lostItem/**")
                .excludePathPatterns("/common/login")//不拦截路径
                .excludePathPatterns("/common/register")
                .excludePathPatterns("/common/upload")
                .excludePathPatterns("/admin/club/page");
    }

    /**
     * 设置静态资源映射
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射...");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 拓展springMVC框架消息转换器
     */
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters){
        log.info("拓展的消息转换器");
        //创建一个消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //需要为消息转换器设置一个对象转换器，对象转换器可以将json对象序列化为json数据
        converter.setObjectMapper(new JacksonObjectMapper());
        //将拓展的消息转换器加入到容器中//把优先级排到第一位，优先使用
        converters.add(0,converter);
    }
}
