package com.lynn.blog.mgr.controller.v1;

import com.lynn.blog.common.result.MultiResult;
import com.lynn.blog.mgr.domain.request.BlogListRequest;
import com.lynn.blog.mgr.domain.response.BlogListResponse;
import com.lynn.blog.mgr.service.BlogService;
import com.lynn.blog.pub.controller.v1.BaseV1Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("{version}/open/blog")
@RestController
public class BlogController extends BaseV1Controller {

    @Autowired
    private BlogService blogService;

    @PostMapping("getBlogListByCategoryId")
    public MultiResult<BlogListResponse> getBlogListByCategoryId(@Valid @RequestBody BlogListRequest request, BindingResult result){
        validate(result);
        return blogService.getBlogListByCategoryId(request);
    }
}
