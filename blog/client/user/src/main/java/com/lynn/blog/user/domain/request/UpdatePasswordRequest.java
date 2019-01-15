package com.lynn.blog.user.domain.request;

import lombok.Data;

@Data
public class UpdatePasswordRequest {

    private String oldPwd;

    private String newPwd;
}
