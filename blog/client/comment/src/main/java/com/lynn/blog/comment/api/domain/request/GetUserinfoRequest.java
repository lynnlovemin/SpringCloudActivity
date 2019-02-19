package com.lynn.blog.comment.api.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class GetUserinfoRequest {

    @NotNull
    private Long userId;
}
