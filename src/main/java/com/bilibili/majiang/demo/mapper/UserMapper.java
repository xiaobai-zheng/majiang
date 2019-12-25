package com.bilibili.majiang.demo.mapper;

import com.bilibili.majiang.demo.model.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface UserMapper extends Mapper<User> {
}
