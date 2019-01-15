package com.lynn.blog.category;

import com.lynn.blog.common.app.Application;
import com.lynn.blog.pub.PublicApplication;

public class CategoryApplication extends PublicApplication {

    public static void main(String[] args) {
        Application.startup(CategoryApplication.class,args);
    }
}
