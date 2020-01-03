package com.bilibili.majiang.demo.mapper;

import com.bilibili.majiang.demo.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface QuestionMapper extends Mapper<Question> {
    @Select("select * from question where creator=#{creator}")
    List<Question> selectQuesionByCreator(@Param("creator") Integer creator);
    @Select("select * from question where id=#{id}")
    Question selectQuesionById(@Param("id") Integer id);
}
