package com.lynn.blog.search.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddBlogRequest {

    @NotNull
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String summary;

}
