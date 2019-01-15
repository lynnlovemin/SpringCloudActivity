package com.lynn.blog.mgr.service.impl;

import com.lynn.blog.common.result.MultiResult;
import com.lynn.blog.common.utils.DateUtils;
import com.lynn.blog.mgr.domain.request.BlogDetailRequest;
import com.lynn.blog.mgr.domain.request.BlogListRequest;
import com.lynn.blog.mgr.domain.response.BlogDetailResponse;
import com.lynn.blog.mgr.domain.response.BlogListResponse;
import com.lynn.blog.mgr.service.BlogService;
import com.lynn.blog.pub.domain.entity.Blog;
import com.lynn.blog.pub.domain.entity.BlogExample;
import com.lynn.blog.pub.mapper.BlogMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public MultiResult<BlogListResponse> getBlogListByCategoryId(BlogListRequest request) {
        BlogExample example = new BlogExample();
        example.setOffset(request.getOffset());
        example.setLimit(request.getLimit());
        example.createCriteria()
                .andCategoryIdEqualTo(request.getCategoryId());
        int count = blogMapper.countByExample(example);
        if(count > 0){
            List<Blog> blogList = blogMapper.selectByExample(example);
            if(null != blogList && blogList.size() > 0){
                List<BlogListResponse> data = new ArrayList<>();
                blogList.stream().forEach(blog -> {
                    BlogListResponse response = new BlogListResponse();
                    //将blog对象属性拷贝到response
                    BeanUtils.copyProperties(blog,response);
                    response.setCreateTime(DateUtils.parseDate2String(blog.getGmtCreate(),"yyyy-MM-dd HH:mm:ss"));
                    data.add(response);
                });
                return MultiResult.buildSuccess(data,count);
            }
            return MultiResult.buildSuccess(new ArrayList<>(),count);
        }
        return MultiResult.buildSuccess(new ArrayList<>(),count);
    }

    @Override
    public MultiResult<BlogListResponse> getHotBlogList() {
        return null;
    }

    @Override
    public MultiResult<BlogDetailResponse> getBlogDetail(BlogDetailRequest request) {
        return null;
    }
}
