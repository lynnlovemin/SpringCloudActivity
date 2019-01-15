package com.lynn.blog.mgr.mapper.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class BlogProvider {

    public String selectBlogListProvider(@Param("title")String title, @Param("offset") int offset, @Param("limit") int limit){
        return new SQL(){
            {
                SELECT("*");
                FROM("blog b,user u");
                WHERE("b.user_id = u.id");
                if(null != title){
                    WHERE("b.title = #{title}");
                }
            }
        }.toString() + "limit #{offset},#{limit}";
    }
}
