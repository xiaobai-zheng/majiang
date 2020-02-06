package com.bilibili.majiang.demo.model;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

@Data
public class Comment {
  @Id
  @KeySql(useGeneratedKeys = true)
  private Long id;
  private String content;
  private Long parentId;
  private Integer type;
  private Long commentator;
  private Long gemCreate;
  private Long gemModified;
  private Long likeCount;

}
