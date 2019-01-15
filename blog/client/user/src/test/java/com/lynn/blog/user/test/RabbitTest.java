//package com.lynn.blog.user.test;
//
//import com.lynn.blog.common.rabbitmq.MyBean;
//import com.lynn.blog.user.UserApplication;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@SpringBootTest(classes = UserApplication.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//public class RabbitTest {
//
//    @Autowired
//    private MyBean myBean;
//
//    @Test
//    public void test(){
//        try {
//            myBean.send("HelloWorld!");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
