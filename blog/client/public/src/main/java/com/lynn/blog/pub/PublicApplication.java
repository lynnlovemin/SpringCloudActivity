package com.lynn.blog.pub;

import com.lynn.blog.common.app.Application;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan(basePackages = "com.lynn.blog.pub.mapper")
@EnableFeignClients(basePackages = "com.lynn.blog")
public class PublicApplication extends Application{
}
