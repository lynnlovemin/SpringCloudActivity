package com.lynn.blog.user.domain.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetUserinfoResponse {

    private Long id;

    private String username;

    private String nickname;

    private String avatar;
}
