package com.lynn.blog.mgr.domain.entity;

import com.lynn.blog.pub.domain.entity.Blog;
import lombok.Data;

@Data
public class SubBlog extends Blog {

    /**
     * 用户名
     */
    private String username;
}
