package com.lynn.blog.common.app;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
@ComponentScan(basePackages = "com.lynn.blog")
public abstract class Application {

    /**
     * 启动应用程序
     * @param cls
     * @param args
     */
    public static void startup(Class<?> cls,String[] args){
        SpringApplication.run(cls,args);
    }
}
