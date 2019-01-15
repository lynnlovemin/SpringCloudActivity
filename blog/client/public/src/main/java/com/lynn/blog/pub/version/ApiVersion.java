package com.lynn.blog.pub.version;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 定义API版本号
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Mapping
@Documented
public @interface ApiVersion {

    /**
     * 设置版本号
     * @return
     */
    int value();
}
