package com.lynn.blog.common.app;

import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
@ComponentScan(basePackages = "com.lynn.blog")
@EnableHystrixDashboard
public abstract class Application {

    /**
     * 启动应用程序
     * @param cls
     * @param args
     */
    public static void startup(Class<?> cls,String[] args){
        SpringApplication.run(cls,args);
    }

    public static void main(String[] args) {
        DateTime dateTime = new DateTime();
        System.out.println(dateTime.plusDays(90).toString("yyyy-MM-dd HH:mm:ss"));
    }
}
