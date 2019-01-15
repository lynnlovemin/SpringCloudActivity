package com.lynn.blog.user.domain.request;

import lombok.Data;

@Data
public class AddBlogRequest {

    private String title;

    private String content;

    private String summary;

    private Integer status;

    private Long categoryId;
}
