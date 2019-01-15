package com.lynn.blog.user.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TestRequest {

    @NotEmpty
    private String name;
}
