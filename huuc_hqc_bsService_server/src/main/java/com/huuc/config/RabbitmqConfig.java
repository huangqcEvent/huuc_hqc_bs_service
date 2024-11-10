package com.huuc.config;

import com.huuc.utils.RabbitMqUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class RabbitmqConfig {
    @Lazy
    private final RabbitTemplate rabbitTemplate;
    @Bean
    public RabbitMqUtils rabbitMqUtils(){
        return new RabbitMqUtils(rabbitTemplate);
    }
}
