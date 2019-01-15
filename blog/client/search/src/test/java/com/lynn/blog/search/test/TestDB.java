package com.lynn.blog.search.test;

import com.lynn.blog.search.SearchApplication;
import com.lynn.blog.search.domain.entity.ESBlog;
import com.lynn.blog.search.domain.request.AddBlogRequest;
import com.lynn.blog.search.domain.request.GetBlogRequest;
import com.lynn.blog.search.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = SearchApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDB {

    @Autowired
    private SearchService searchService;

    @Test
    public void test(){
        try {
//            AddBlogRequest blog = new AddBlogRequest();
//            blog.setId(1L);
//            blog.setTitle("测试标题");
//            blog.setSummary("测试摘要");
//            searchService.addBlog(blog);
            GetBlogRequest request = new GetBlogRequest();
            request.setKey("摘要");
            System.out.println(searchService.getBlog(request));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
