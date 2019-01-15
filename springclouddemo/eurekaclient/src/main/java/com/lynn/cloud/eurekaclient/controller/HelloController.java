package com.lynn.cloud.eurekaclient.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("index")
    public String index(){
        return "这是一个eurekaclient";
    }
}
