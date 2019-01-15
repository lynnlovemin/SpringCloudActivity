package com.lynn.blog.comment.service.impl;

import com.lynn.blog.comment.domain.request.CommentAddRequest;
import com.lynn.blog.comment.domain.request.CommentListRequest;
import com.lynn.blog.comment.domain.response.CommentListResponse;
import com.lynn.blog.comment.service.CommentService;
import com.lynn.blog.common.result.MultiResult;
import com.lynn.blog.common.result.SingleResult;
import com.lynn.blog.common.utils.DateUtils;
import com.lynn.blog.pub.domain.entity.Comment;
import com.lynn.blog.pub.domain.entity.CommentExample;
import com.lynn.blog.pub.mapper.CommentMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SingleResult<String> addComment(CommentAddRequest request, Long userId) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(request,comment);
        comment.setUserId(userId);
        commentMapper.insert(comment);
        return SingleResult.buildSuccessWithoutData();
    }

    @Override
    public MultiResult<CommentListResponse> getCommentList(CommentListRequest request) {
        CommentExample example = new CommentExample();
        example.createCriteria()
                .andBlogIdEqualTo(request.getBlogId());
        long count = commentMapper.countByExample(example);
        example.setLimit(request.getLimit());
        example.setOffset(request.getStart());
        if(count > 0){
            List<Comment> commentList = commentMapper.selectByExample(example);
            if(null != commentList && commentList.size() > 0){
                List<CommentListResponse> data = new ArrayList<>();
                commentList.stream().forEach(comment -> {
                    CommentListResponse response = new CommentListResponse();
                    BeanUtils.copyProperties(comment,response);
                    response.setCreateTime(DateUtils.parseDate2String(comment.getGmtCreate(),"yyyy-MM-dd HH:mm:ss"));
                    data.add(response);
                });
                return MultiResult.buildSuccess(data,count);
            }
            return MultiResult.buildSuccessWithoutTotal(new ArrayList<>());
        }
        return MultiResult.buildSuccessWithoutTotal(new ArrayList<>());
    }
}
