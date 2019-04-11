package com.lynn.blog.mgr.mapper;

import com.lynn.blog.mgr.domain.entity.SubBlog;
import com.lynn.blog.mgr.mapper.provider.BlogProvider;
import com.lynn.blog.pub.domain.entity.Blog;
import com.lynn.blog.pub.mapper.BlogMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface SubBlogMapper extends BlogMapper {

    @Select("<script>select * from blog b,user u where b.user_id = u.id <if test=\"null != title\">and b.title = #{title}</if> limit #{offset},#{limit}</script>")
    List<SubBlog> selectBlogList(@Param("title")String title,@Param("offset") int offset,@Param("limit") int limit);

//    @SelectProvider(type= BlogProvider.class,method = "selectBlogListProvider")
//    List<SubBlog> selectBlogList(@Param("title")String title,@Param("offset") int offset,@Param("limit") int limit);
}
