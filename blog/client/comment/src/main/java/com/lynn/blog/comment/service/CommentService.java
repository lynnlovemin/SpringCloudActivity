package com.lynn.blog.comment.service;

import com.lynn.blog.comment.domain.request.CommentAddRequest;
import com.lynn.blog.comment.domain.request.CommentListRequest;
import com.lynn.blog.comment.domain.response.CommentListResponse;
import com.lynn.blog.common.result.MultiResult;
import com.lynn.blog.common.result.SingleResult;

public interface CommentService {

    /**
     * 发表评论
     * @param request
     * @param userId
     * @return
     */
    SingleResult<String> addComment(CommentAddRequest request,Long userId);

    /**
     * 分页获得博客下面的评论列表
     * @param request
     * @return
     */
    MultiResult<CommentListResponse> getCommentList(CommentListRequest request);
}
