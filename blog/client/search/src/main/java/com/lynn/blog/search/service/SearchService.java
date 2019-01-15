package com.lynn.blog.search.service;

import com.lynn.blog.common.result.MultiResult;
import com.lynn.blog.common.result.SingleResult;
import com.lynn.blog.search.domain.entity.ESBlog;
import com.lynn.blog.search.domain.request.AddBlogRequest;
import com.lynn.blog.search.domain.request.DeleteBlogRequest;
import com.lynn.blog.search.domain.request.GetBlogRequest;
import com.lynn.blog.search.domain.response.BlogResponse;

public interface SearchService {

    /**
     * 添加博客信息到数据库
     * @param blog
     */
    SingleResult<String> addBlog(AddBlogRequest request) throws Exception;

    /**
     * 根据关键词分页搜索博客列表
     * @param request
     * @return
     * @throws Exception
     */
    MultiResult<BlogResponse> getBlog(GetBlogRequest request)throws Exception;

    /**
     * 删除博客
     * @param request
     * @return
     * @throws Exception
     */
    SingleResult<String> deleteBlog(DeleteBlogRequest request)throws Exception;
}
