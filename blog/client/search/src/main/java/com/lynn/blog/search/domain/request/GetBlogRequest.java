package com.lynn.blog.search.domain.request;

import com.lynn.blog.pub.domain.request.PageRequest;
import lombok.Data;

@Data
public class GetBlogRequest extends PageRequest {

    private String key;
}
