package com.lynn.blog.test;

import com.lynn.blog.common.app.Application;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class TestApplication extends Application {

    public static void main(String[] args) {
        Application.startup(TestApplication.class,args);
    }
}
