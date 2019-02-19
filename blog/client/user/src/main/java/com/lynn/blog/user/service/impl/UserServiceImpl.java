package com.lynn.blog.user.service.impl;

import com.lynn.blog.common.redis.Redis;
import com.lynn.blog.common.result.MultiResult;
import com.lynn.blog.common.result.SingleResult;
import com.lynn.blog.common.utils.Utils;
import com.lynn.blog.pub.domain.entity.User;
import com.lynn.blog.pub.domain.entity.UserExample;
import com.lynn.blog.pub.domain.request.PageRequest;
import com.lynn.blog.pub.mapper.BlogMapper;
import com.lynn.blog.pub.mapper.UserMapper;
import com.lynn.blog.user.domain.request.*;
import com.lynn.blog.user.domain.response.GetMyBlogResponse;
import com.lynn.blog.user.domain.response.GetMyCollectBlogResponse;
import com.lynn.blog.user.domain.response.GetUserinfoResponse;
import com.lynn.blog.user.domain.response.TokenResponse;
import com.lynn.blog.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Value("${api.encrypt.key}")
    private String key;

    @Autowired
    private Redis redis;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BlogMapper blogMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SingleResult<TokenResponse> register(RegisterRequest request) {
//        List<User> userList = userMapper.selectByUsername(request.getUsername());
//        if(null == userList || userList.size() == 0){
//            User user = new User();
//            BeanUtils.copyProperties(request,user);
//            user.setPassword(Utils.encryptPassword(user.getPassword(),key));
//            userMapper.insert(user);
//            String token = Utils.generateToken(user.getUsername(),key);
//            redis.set(token,user.getUsername());
//            TokenResponse response = new TokenResponse();
//            response.setToken(token);
//            return SingleResult.buildSuccess(response);
//        }
        return SingleResult.buildFailure("用户名已存在！");
    }

    @Override
    public SingleResult<TokenResponse> login(LoginRequest request) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andUsernameEqualTo(request.getUsername());
        List<User> userList = userMapper.selectByExample(example);
        if(null != userList && userList.size() > 0){
            User user = userList.get(0);
            if(user.getPassword().equals(Utils.encryptPassword(request.getPassword(),key))){
                String token = Utils.generateToken(user.getUsername(),key);
                redis.set(token,user.getId()+"");
                TokenResponse response = new TokenResponse();
                response.setToken(token);
                return SingleResult.buildSuccess(response);
            }
            return SingleResult.buildFailure("用户名或密码输入错误！");
        }
        return SingleResult.buildFailure("用户名或密码输入错误！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SingleResult<String> updatePassword(UpdatePasswordRequest request, Long userId) {
//        User user = userMapper.selectById(userId);
//        if(null != user){
//            if(user.getPassword().equals(Utils.encryptPassword(request.getOldPwd(),key))){
//                userMapper.updatePassword(request.getNewPwd(),userId);
//                return SingleResult.buildSuccessWithMessage("密码修改成功！");
//            }else{
//                return SingleResult.buildFailure("旧密码输入不正确！");
//            }
//        }
        return SingleResult.buildFailure();
    }

    @Override
    public MultiResult<GetMyBlogResponse> getMyBlog(PageRequest request, Long userId) {
//        long count = blogMapper.selectCountByUserId(userId);
//        if(count > 0){
//            List<Blog> blogList = blogMapper.selectByUserId(userId,request.getStart(),request.getLimit());
//            if(null != blogList && blogList.size() > 0){
//                List<GetMyBlogResponse> data = new ArrayList<>();
//                blogList.stream().forEach(blog -> {
//                    GetMyBlogResponse response = new GetMyBlogResponse();
//                    BeanUtils.copyProperties(blog,response);
//                    response.setBlogId(blog.getId());
//                    response.setCreateTime(DateUtils.parseDate2String(blog.getGmtCreate(),"yyyy-MM-dd HH:mm:ss"));
//                    data.add(response);
//                });
//                return MultiResult.buildSuccess(data,count);
//            }
//            return MultiResult.buildSuccess(new ArrayList<>(),count);
//        }
        return MultiResult.buildSuccess(new ArrayList<>(),0);
    }

    @Override
    public MultiResult<GetMyCollectBlogResponse> getMyCollectBlog(PageRequest request, Long userId) {
//        long count = blogMapper.selectCountCollectByUserId(userId);
//        if(count > 0){
//            List<Blog> blogList = blogMapper.selectCollectByUserId(userId,request.getStart(),request.getLimit());
//            if(null != blogList && blogList.size() > 0){
//                List<GetMyCollectBlogResponse> data = new ArrayList<>();
//                blogList.stream().forEach(blog -> {
//                    GetMyCollectBlogResponse response = new GetMyCollectBlogResponse();
//                    BeanUtils.copyProperties(blog,response);
//                    response.setBlogId(blog.getId());
//                    response.setCreateTime(DateUtils.parseDate2String(blog.getGmtCreate(),"yyyy-MM-dd HH:mm:ss"));
//                    data.add(response);
//                });
//                return MultiResult.buildSuccess(data,count);
//            }
//            return MultiResult.buildSuccess(new ArrayList<>(),count);
//        }
        return MultiResult.buildSuccess(new ArrayList<>(),0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SingleResult<AddBlogRequest> addBlog(AddBlogRequest request, Long userId) {
        return null;
    }

    @Override
    public SingleResult<GetUserinfoResponse> getUserinfo(GetUserinfoRequest request) {
        User user = userMapper.selectByPrimaryKey(request.getUserId());
        GetUserinfoResponse response = new GetUserinfoResponse();
        BeanUtils.copyProperties(user,response);
        return SingleResult.buildSuccess(response);
    }
}
