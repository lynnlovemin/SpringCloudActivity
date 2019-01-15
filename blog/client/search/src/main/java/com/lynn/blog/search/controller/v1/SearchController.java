package com.lynn.blog.search.controller.v1;

import com.lynn.blog.common.result.MultiResult;
import com.lynn.blog.common.result.SingleResult;
import com.lynn.blog.pub.controller.v1.BaseV1Controller;
import com.lynn.blog.search.domain.request.AddBlogRequest;
import com.lynn.blog.search.domain.request.DeleteBlogRequest;
import com.lynn.blog.search.domain.request.GetBlogRequest;
import com.lynn.blog.search.domain.response.BlogResponse;
import com.lynn.blog.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("SearchV1Controller")
@RequestMapping("{version}/open/search")
public class SearchController extends BaseV1Controller {

    @Autowired
    private SearchService searchService;

    @PostMapping("addBlog")
    public SingleResult<String> addBlog(@Valid @RequestBody AddBlogRequest request, BindingResult result) throws Exception{
        validate(result);
        return searchService.addBlog(request);
    }

    @PostMapping("getBlog")
    public MultiResult<BlogResponse> getBlog(@Valid @RequestBody GetBlogRequest request,BindingResult result)throws Exception{
        validate(result);
        return searchService.getBlog(request);
    }

    @PostMapping("deleteBlog")
    public SingleResult<String> deleteBlog(@Valid @RequestBody DeleteBlogRequest request,BindingResult result)throws Exception{
        validate(result);
        return searchService.deleteBlog(request);
    }
}
