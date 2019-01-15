package com.lynn.blog.mgr.test;

import com.lynn.blog.mgr.BlogApplication;
import com.lynn.blog.mgr.mapper.SubBlogMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class TestDB {

    @Autowired
    private SubBlogMapper subBlogMapper;

    @Test
    public void test(){
        try {
            System.out.println(subBlogMapper.selectBlogList("xx",0,10));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
