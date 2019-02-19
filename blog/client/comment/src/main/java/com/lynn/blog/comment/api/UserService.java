package com.lynn.blog.comment.api;

import com.lynn.blog.comment.api.domain.request.GetUserinfoRequest;
import com.lynn.blog.comment.api.domain.response.GetUserinfoResponse;
import com.lynn.blog.comment.api.impl.UserServiceError;
import com.lynn.blog.common.result.SingleResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user",fallback = UserServiceError.class)
public interface UserService {

    @PostMapping("/v1/user/open/getUserinfo")
    SingleResult<GetUserinfoResponse> getUserinfo(@RequestBody GetUserinfoRequest request);
}
