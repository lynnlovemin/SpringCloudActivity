package com.lynn.blog.category.service.impl;

import com.lynn.blog.category.domain.response.CategoryListResponse;
import com.lynn.blog.category.service.CategoryService;
import com.lynn.blog.common.result.MultiResult;
import com.lynn.blog.common.tree.TreeUtils;
import com.lynn.blog.pub.domain.entity.Category;
import com.lynn.blog.pub.domain.entity.CategoryExample;
import com.lynn.blog.pub.mapper.CategoryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public MultiResult<CategoryListResponse> getCategoryList() {
        List<Category> categoryList = categoryMapper.selectByExample(new CategoryExample());
        if(null != categoryList && categoryList.size() > 0){
            List<CategoryListResponse> data = new ArrayList<>();
            categoryList.stream().forEach(item -> {
                CategoryListResponse response = new CategoryListResponse();
                BeanUtils.copyProperties(item,response);
                data.add(response);
            });
            return MultiResult.buildSuccessWithoutTotal(TreeUtils.convert2Tree(data,null));
        }
        return MultiResult.buildSuccessWithoutTotal(new ArrayList<>());
    }
}
