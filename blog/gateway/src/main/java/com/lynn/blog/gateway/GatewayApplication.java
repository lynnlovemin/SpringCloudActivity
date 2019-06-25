package com.lynn.blog.gateway;

import com.lynn.blog.common.app.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
@ComponentScan(basePackages = "com.lynn.blog")
public class GatewayApplication{

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }
}
