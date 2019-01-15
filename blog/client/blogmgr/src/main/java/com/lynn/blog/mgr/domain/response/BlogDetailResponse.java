package com.lynn.blog.mgr.domain.response;

import lombok.Data;

@Data
public class BlogDetailResponse {

    private Long id;

    private String title;

    private String content;

    private String avatar;

    private String username;

    private String createTime;

    private Integer viewCount;

    private Integer collectCount;
}
