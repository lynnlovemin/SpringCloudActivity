package com.lynn.blog.comment.domain.response;

import lombok.Data;

@Data
public class CommentListResponse {

    private Long id;

    private String content;

    private String username;

    private String createTime;

    private String parentUsername;
}
