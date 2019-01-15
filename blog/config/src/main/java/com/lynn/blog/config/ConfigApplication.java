package com.lynn.blog.config;

import com.lynn.blog.common.app.Application;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
public class ConfigApplication extends Application{

    public static void main(String[] args) throws Exception{
        Application.startup(ConfigApplication.class,args);
    }
}
