package com.lynn.blog.category.domain.response;

import com.lynn.blog.common.tree.annotation.Children;
import com.lynn.blog.common.tree.annotation.ParentId;
import com.lynn.blog.common.tree.annotation.TreeId;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryListResponse {

    @TreeId
    private Long id;

    private String name;

    @ParentId
    private Long parentId;

    @Children
    private List<CategoryListResponse> children = new ArrayList<>();
}
