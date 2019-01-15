package com.lynn.blog.category.service;

import com.lynn.blog.category.domain.response.CategoryListResponse;
import com.lynn.blog.common.result.MultiResult;

public interface CategoryService {

    /**
     * 获得分类列表（支持树状接口）
     * @return
     */
    MultiResult<CategoryListResponse> getCategoryList();
}
