package com.lynn.blog.user.controller.v2;

import com.lynn.blog.pub.controller.v2.BaseV2Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("{version}")
@RestController
public class TestV2Controller extends BaseV2Controller {

    @RequestMapping("index")
    public String index(){
        return "Hello V2";
    }
}
