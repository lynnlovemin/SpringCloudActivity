package com.lynn.blog.user.domain.response;

import lombok.Data;

@Data
public class GetMyCollectBlogResponse {

    private Long blogId;

    private String title;

    private String createTime;
}
