package com.lynn.blog.register;

import com.lynn.blog.common.app.Application;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringCloudApplication
public class RegisterApplication extends Application {

    public static void main(String[] args) {
        Application.startup(RegisterApplication.class,args);
    }
}
