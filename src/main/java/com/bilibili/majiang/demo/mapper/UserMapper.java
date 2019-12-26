package com.bilibili.majiang.demo.mapper;

import com.bilibili.majiang.demo.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface UserMapper extends Mapper<User> {
    @Select("select * from user where account_id=#{accountId}")
    User selectByAccountId(@Param("accountId")String accountId);
}
