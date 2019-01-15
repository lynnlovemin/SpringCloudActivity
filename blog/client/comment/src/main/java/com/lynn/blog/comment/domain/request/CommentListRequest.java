package com.lynn.blog.comment.domain.request;

import com.lynn.blog.pub.domain.request.PageRequest;
import lombok.Data;

@Data
public class CommentListRequest extends PageRequest {

    private Long blogId;
}
