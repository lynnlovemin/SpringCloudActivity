package com.lynn.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println(args);
    }
}
