package com.huuc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //连接工厂
        template.setConnectionFactory(connectionFactory);
        //键序列化器
        template.setKeySerializer(new StringRedisSerializer());
        //值序列化器
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
