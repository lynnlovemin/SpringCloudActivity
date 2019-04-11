package com.lynn.blog.pub.config;

import feign.Contract;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class MyFeignConfiguration {

    @Bean
    public Contract feignContract(){
        return new feign.Contract.Default();
    }
}
