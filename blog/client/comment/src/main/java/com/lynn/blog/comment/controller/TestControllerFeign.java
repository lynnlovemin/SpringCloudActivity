package com.lynn.blog.comment.controller;

import com.lynn.blog.comment.api.TestServiceFeign;
import com.lynn.blog.comment.api.TestServiceRibbon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("feign")
@RestController
public class TestControllerFeign {

    @Autowired
    TestServiceFeign testServiceFeign;

    @RequestMapping("test")
    private String test(){
        return testServiceFeign.test();
    }
}
