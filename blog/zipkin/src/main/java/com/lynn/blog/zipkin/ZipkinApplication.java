package com.lynn.blog.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;

/**
 * 自建zipkin服务器
 * springboot2.0官方不推荐自建zipkin服务器
 * 而是直接提供了编译好的jar包供我们使用，
 * curl -sSL https://zipkin.io/quickstart.sh | bash -s
 * java -jar zipkin.jar
 */
@SpringBootApplication
@EnableZipkinServer
public class ZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinApplication.class,args);
    }
}
