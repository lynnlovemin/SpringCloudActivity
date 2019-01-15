package com.lynn.blog.mgr.domain.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BlogListRequest {

    @NotNull
    private Long categoryId;
    @NotNull
    private Integer offset;
    @NotNull
    private Integer limit;
}
