package com.lynn.blog.gateway;

import com.lynn.blog.common.app.Application;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class GatewayApplication extends Application{

    public static void main(String[] args) {
        Application.startup(GatewayApplication.class,args);
    }
}
