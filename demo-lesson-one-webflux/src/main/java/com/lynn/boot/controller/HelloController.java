package com.lynn.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

    @RequestMapping(value = "hello")
    public Mono<String> hello(){
        return Mono.just("Hello World!");
    }
}
