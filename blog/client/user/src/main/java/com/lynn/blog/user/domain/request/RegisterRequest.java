package com.lynn.blog.user.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegisterRequest {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
