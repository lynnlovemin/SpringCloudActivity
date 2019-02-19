package com.lynn.blog.user.service;

import com.lynn.blog.common.result.MultiResult;
import com.lynn.blog.common.result.SingleResult;
import com.lynn.blog.pub.domain.request.PageRequest;
import com.lynn.blog.user.domain.request.*;
import com.lynn.blog.user.domain.response.GetMyBlogResponse;
import com.lynn.blog.user.domain.response.GetMyCollectBlogResponse;
import com.lynn.blog.user.domain.response.GetUserinfoResponse;
import com.lynn.blog.user.domain.response.TokenResponse;

public interface UserService {

    /**
     * 注册，生成token
     * username存在，则不允许注册
     * @param request
     * @return
     */
    SingleResult<TokenResponse> register(RegisterRequest request);

    /**
     * 登录，生成token
     * @param request
     * @return
     */
    SingleResult<TokenResponse> login(LoginRequest request);

    /**
     * 修改密码
     * @param request
     * @param userId
     * @return
     */
    SingleResult<String> updatePassword(UpdatePasswordRequest request,Long userId);

    /**
     * 获得我发表的博客列表
     * @param request
     * @param userId
     * @return
     */
    MultiResult<GetMyBlogResponse> getMyBlog(PageRequest request, Long userId);

    /**
     * 获得我收藏的博客列表
     * @param request
     * @param userId
     * @return
     */
    MultiResult<GetMyCollectBlogResponse> getMyCollectBlog(PageRequest request, Long userId);

    /**
     * 发表博客
     * @param request
     * @param userId
     * @return
     */
    SingleResult<AddBlogRequest> addBlog(AddBlogRequest request,Long userId);

    /**
     * 获得用户信息
     * @param request
     * @return
     */
    SingleResult<GetUserinfoResponse> getUserinfo(GetUserinfoRequest request);
}
