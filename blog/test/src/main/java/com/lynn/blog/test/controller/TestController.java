package com.lynn.blog.test.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("test")
    public String test(){
        try {
            Thread.sleep(40000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return port;
    }
}
