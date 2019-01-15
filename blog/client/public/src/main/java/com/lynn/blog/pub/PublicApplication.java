package com.lynn.blog.pub;

import com.lynn.blog.common.app.Application;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.lynn.blog.pub.mapper")
public class PublicApplication extends Application{
}
