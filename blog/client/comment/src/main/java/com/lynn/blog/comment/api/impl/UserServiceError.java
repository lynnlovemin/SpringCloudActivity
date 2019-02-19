package com.lynn.blog.comment.api.impl;

import com.lynn.blog.comment.api.UserService;
import com.lynn.blog.comment.api.domain.request.GetUserinfoRequest;
import com.lynn.blog.comment.api.domain.response.GetUserinfoResponse;
import com.lynn.blog.common.result.SingleResult;
import org.springframework.stereotype.Component;

@Component
public class UserServiceError implements UserService {

    @Override
    public SingleResult<GetUserinfoResponse> getUserinfo(GetUserinfoRequest request) {
        return SingleResult.buildFailure("服务器异常！");
    }
}
