package com.lynn.blog.test.controller;

import com.lynn.blog.test.hystrix.TestHystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.HystrixProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {

    @Value("${data.message}")
    private String message;

    @Value("${server.port}")
    private int port;

    @RequestMapping("test")
    public String test(){
        return "当前端口："+port;
    }

    @RequestMapping("testHystrix")
    public String testHystrix(){
        TestHystrixCommand hystrixCommand = new TestHystrixCommand("test");
        return hystrixCommand.execute();
    }
}
