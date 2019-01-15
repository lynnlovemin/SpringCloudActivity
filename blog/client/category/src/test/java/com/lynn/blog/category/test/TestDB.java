package com.lynn.blog.category.test;

import com.lynn.blog.category.CategoryApplication;
import com.lynn.blog.category.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CategoryApplication.class)
public class TestDB {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void test(){
        try {
//            System.out.println(categoryService.getCategoryList());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
