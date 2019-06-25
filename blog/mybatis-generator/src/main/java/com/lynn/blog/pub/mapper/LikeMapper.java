package com.lynn.blog.pub.mapper;

import com.lynn.blog.pub.domain.entity.Like;
import com.lynn.blog.pub.domain.entity.LikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LikeMapper {
    int countByExample(LikeExample example);

    int deleteByExample(LikeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Like record);

    int insertSelective(Like record);

    List<Like> selectByExample(LikeExample example);

    Like selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Like record, @Param("example") LikeExample example);

    int updateByExample(@Param("record") Like record, @Param("example") LikeExample example);

    int updateByPrimaryKeySelective(Like record);

    int updateByPrimaryKey(Like record);
}