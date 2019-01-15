package com.lynn.boot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${self.message.data}")
    private String value;


    @RequestMapping(value = "hello",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String hello(){
        return value;
    }

}

