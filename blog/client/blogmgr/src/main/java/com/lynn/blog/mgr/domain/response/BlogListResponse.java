package com.lynn.blog.mgr.domain.response;

import lombok.Data;

@Data
public class BlogListResponse {

    private Long id;

    private String title;

    private String summary;

    private String createTime;

    private Integer viewCount;

}
