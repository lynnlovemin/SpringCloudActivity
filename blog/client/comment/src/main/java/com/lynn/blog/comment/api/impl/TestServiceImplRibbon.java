package com.lynn.blog.comment.api.impl;

import com.lynn.blog.comment.api.TestServiceRibbon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestServiceImplRibbon implements TestServiceRibbon {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String test() {
        return restTemplate.postForEntity("http://TEST/test",null,String.class).getBody();
    }

}
