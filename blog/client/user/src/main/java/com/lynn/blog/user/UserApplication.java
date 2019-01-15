package com.lynn.blog.user;

import com.lynn.blog.common.app.Application;
import com.lynn.blog.pub.PublicApplication;

public class UserApplication extends PublicApplication {

    public static void main(String[] args) {
        Application.startup(UserApplication.class,args);
    }
}
