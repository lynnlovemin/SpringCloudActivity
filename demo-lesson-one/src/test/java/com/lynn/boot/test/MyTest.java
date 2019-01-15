package com.lynn.boot.test;

import com.lynn.boot.Application;
import com.lynn.boot.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MyTest {

    @Autowired
    private Person person;

    @Test
    public void test(){
        System.out.println(person);
    }
}
