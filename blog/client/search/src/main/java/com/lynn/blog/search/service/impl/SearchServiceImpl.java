package com.lynn.blog.search.service.impl;


import com.lynn.blog.common.result.MultiResult;
import com.lynn.blog.common.result.SingleResult;
import com.lynn.blog.search.domain.entity.ESBlog;
import com.lynn.blog.search.domain.request.AddBlogRequest;
import com.lynn.blog.search.domain.request.DeleteBlogRequest;
import com.lynn.blog.search.domain.request.GetBlogRequest;
import com.lynn.blog.search.domain.response.BlogResponse;
import com.lynn.blog.search.service.SearchService;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private JestClient jestClient;

    /**
     * 表名
     */
    private static final String TABLE_NAME = "blog-table";
    private static final String INDEX_NAME = "blog-index";
    /**
     * 搜索关键词的字段
     */
    private static final String FIELD_NAMES = "title,summary";

    @Override
    public SingleResult<String> addBlog(AddBlogRequest request) throws Exception{
        ESBlog blog = new ESBlog();
        BeanUtils.copyProperties(request,blog);
        Index index = new Index.Builder(blog).index(INDEX_NAME).type(TABLE_NAME).build();
        jestClient.execute(index);
        return SingleResult.buildSuccessWithoutData();
    }

    @Override
    public MultiResult<BlogResponse> getBlog(GetBlogRequest request)throws Exception{
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.multiMatchQuery(request.getKey(),FIELD_NAMES.split(",")))
                .from(request.getStart())
                .size(request.getLimit());
        Search search = new Search.Builder(builder.toString())
                .addIndex(INDEX_NAME)
                .addType(TABLE_NAME).build();
        JestResult ret = jestClient.execute(search);
        List<ESBlog> blogList = ret.getSourceAsObjectList(ESBlog.class);
        List<BlogResponse> data = new ArrayList<>();
        if(null != blogList && blogList.size() > 0){
            blogList.stream().forEach(item -> {
                BlogResponse response = new BlogResponse();
                BeanUtils.copyProperties(item,response);
                data.add(response);
            });
        }
        return MultiResult.buildSuccessWithoutTotal(data);
    }

    @Override
    public SingleResult<String> deleteBlog(DeleteBlogRequest request)throws Exception{
        Delete delete = new Delete.Builder(request.getId()).index(INDEX_NAME)
                .type(TABLE_NAME).build();
        jestClient.execute(delete);
        return SingleResult.buildSuccessWithoutData();
    }

}
