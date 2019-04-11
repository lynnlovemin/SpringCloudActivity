package com.lynn.blog.comment.api.impl;

import com.lynn.blog.comment.api.TestServiceFeign;
import com.lynn.blog.comment.api.domain.request.GetUserinfoRequest;
import com.lynn.blog.comment.api.domain.response.GetUserinfoResponse;
import com.lynn.blog.common.result.SingleResult;
import org.springframework.stereotype.Component;

@Component
public class TestServiceErrorFeign implements TestServiceFeign {

    @Override
    public String test() {
        return "服务器异常！";
    }
}
