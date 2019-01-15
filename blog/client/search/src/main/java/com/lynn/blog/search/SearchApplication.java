package com.lynn.blog.search;

import com.lynn.blog.common.app.Application;
import com.lynn.blog.pub.PublicApplication;
import org.springframework.boot.SpringApplication;

public class SearchApplication extends PublicApplication{

    public static void main(String[] args) {
        Application.startup(SearchApplication.class,args);
    }
}
