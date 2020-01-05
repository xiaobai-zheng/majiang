package com.bilibili.majiang.demo.model;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

@Data
public class Question {

  @Id
  @KeySql(useGeneratedKeys = true)
  private Integer id;
  private String title;
  private String description;
  private String tag;
  private Long gemCreate;
  private Long gemModified;
  private Integer creator;
  private Integer commentCount;
  private Integer viewCount;
  private Integer likeCount;
}
