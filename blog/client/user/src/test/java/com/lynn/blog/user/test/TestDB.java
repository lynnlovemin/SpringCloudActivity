package com.lynn.blog.user.test;

import com.lynn.blog.common.rabbitmq.MyBean;
import com.lynn.blog.pub.domain.entity.UserExample;
import com.lynn.blog.pub.domain.request.PageRequest;
import com.lynn.blog.pub.mapper.UserMapper;
import com.lynn.blog.user.UserApplication;
import com.lynn.blog.user.domain.request.LoginRequest;
import com.lynn.blog.user.domain.request.RegisterRequest;
import com.lynn.blog.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = UserApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDB {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MyBean myBean;

    @Test
    public void test(){
        try {
            myBean.send("hello");
//            UserExample example = new UserExample();
//            example.createCriteria()
//                    .andUsernameEqualTo("xxx");
//            userMapper.selectByExample(example);
//            System.out.println(userMapper.selectByPrimaryKey(1L));
//            RegisterRequest request = new RegisterRequest();
//            request.setUsername("lynn");
//            request.setPassword("1");
//            System.out.println(userService.register(request));
//            LoginRequest request = new LoginRequest();
//            request.setUsername("lynn");
//            request.setPassword("1");
//            System.out.println(userService.login(request));
//            PageRequest request = new PageRequest();
//            request.setPageNumber(1);
//            request.setPageSize(10);
//            System.out.println(userService.getMyBlog(request,1L));
//            PageRequest request = new PageRequest();
//            request.setPageNumber(1);
//            request.setPageSize(10);
//            System.out.println(userService.getMyCollectBlog(request,1L));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
