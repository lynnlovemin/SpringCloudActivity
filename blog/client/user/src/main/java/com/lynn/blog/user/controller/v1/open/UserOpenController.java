package com.lynn.blog.user.controller.v1.open;

import com.lynn.blog.common.result.SingleResult;
import com.lynn.blog.pub.controller.v1.BaseV1Controller;
import com.lynn.blog.user.domain.request.GetUserinfoRequest;
import com.lynn.blog.user.domain.response.GetUserinfoResponse;
import com.lynn.blog.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("{version}/user/open")
public class UserOpenController extends BaseV1Controller {

    @Autowired
    private UserService userService;

    @PostMapping("getUserinfo")
    public SingleResult<GetUserinfoResponse> getUserinfo(@Valid @RequestBody GetUserinfoRequest request, BindingResult result){
        validate(result);
        return userService.getUserinfo(request);
    }
}
