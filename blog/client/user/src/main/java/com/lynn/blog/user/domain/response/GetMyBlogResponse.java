package com.lynn.blog.user.domain.response;

import lombok.Data;

@Data
public class GetMyBlogResponse {

    private Long blogId;

    private String title;

    private String createTime;
}
