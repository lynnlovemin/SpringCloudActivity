package com.lynn.blog.search.domain.entity;

import io.searchbox.annotations.JestId;
import lombok.Data;

@Data
public class ESBlog {

    @JestId
    private Long id;

    private String title;

    private String summary;

}
