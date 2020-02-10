package com.bilibili.majiang.demo.mapper;

import com.bilibili.majiang.demo.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface QuestionMapper extends Mapper<Question> {
    @Update("update question set view_count=view_count+1 where id=#{id}")
    int incViewCount(@Param("id") Long id);
    @Update("update question set comment_count=comment_count+1 where id=#{id}")
    int incCommentCount(@Param("id") Long id);
    //regexp正则表达式
    @Select("select * from QUESTION where TAG regexp #{tag} and id != #{id}")
    List<Question> selectTagLike(@Param("id") Long id,@Param("tag") String tag);
}
