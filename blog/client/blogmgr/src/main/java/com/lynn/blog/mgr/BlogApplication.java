package com.lynn.blog.mgr;

import com.lynn.blog.common.app.Application;
import com.lynn.blog.pub.PublicApplication;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.lynn.blog.mgr.mapper")
public class BlogApplication extends PublicApplication {

    public static void main(String[] args) {
        Application.startup(BlogApplication.class,args);
    }
}
