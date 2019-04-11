package com.lynn.boot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TestApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        System.out.println(args);
    }
}
