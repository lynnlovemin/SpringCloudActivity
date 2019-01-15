package com.lynn.blog.comment.domain.request;

import lombok.Data;

@Data
public class CommentAddRequest {

    private Long blogId;

    private String content;

    private Long parentId;
}
