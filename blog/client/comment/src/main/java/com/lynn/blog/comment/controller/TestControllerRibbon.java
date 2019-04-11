package com.lynn.blog.comment.controller;

import com.lynn.blog.comment.api.TestServiceRibbon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("ribbon")
@RestController
public class TestControllerRibbon {

    @Autowired
    TestServiceRibbon testServiceRibbon;

    @RequestMapping("test")
    private String test(){
        return testServiceRibbon.test();
    }
}
