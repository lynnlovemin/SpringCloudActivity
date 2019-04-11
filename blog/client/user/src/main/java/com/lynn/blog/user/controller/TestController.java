package com.lynn.blog.user.controller;

import com.lynn.blog.common.rabbitmq.MyBean;
import com.lynn.blog.pub.controller.v1.BaseV1Controller;
import com.lynn.blog.user.domain.request.TestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("")
@RestController
public class TestController{

    @Autowired
    private MyBean myBean;

    @GetMapping("index")
    public String index(){
        myBean.send("Hello World!");
        return "发送消息成功！";
    }
}
