package com.lynn.blog.mgr.service;

import com.lynn.blog.common.result.MultiResult;
import com.lynn.blog.mgr.domain.request.BlogDetailRequest;
import com.lynn.blog.mgr.domain.request.BlogListRequest;
import com.lynn.blog.mgr.domain.response.BlogDetailResponse;
import com.lynn.blog.mgr.domain.response.BlogListResponse;

/**
 * @author lynn
 */
public interface BlogService {

    /**
     * 根据分类ID获得博客列表
     * @param request
     * @return
     */
    MultiResult<BlogListResponse> getBlogListByCategoryId(BlogListRequest request);

    /**
     * 获得热门博客，根据浏览量
     * @return
     */
    MultiResult<BlogListResponse> getHotBlogList();

    /**
     * 获得博客详情（浏览量+1）
     * @param request
     * @return
     */
    MultiResult<BlogDetailResponse> getBlogDetail(BlogDetailRequest request);
}
