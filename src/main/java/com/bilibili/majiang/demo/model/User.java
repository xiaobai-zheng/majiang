package com.bilibili.majiang.demo.model;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
@Data
public class User {
  @Id
  @KeySql(useGeneratedKeys = true)
  private Integer id;
  private String accountId;
  private String name;
  private String token;
  private Long gemCreate;
  private Long gemModified;

}
