package com.lynn.blog.common.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class RabbitConfiguration {

    @Bean
    public Queue queue(){
        return new Queue("someQueue");
    }
}
