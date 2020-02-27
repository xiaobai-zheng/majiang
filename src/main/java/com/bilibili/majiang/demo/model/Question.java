package com.bilibili.majiang.demo.model;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="question")
public class Question {

  @Id
  @KeySql(useGeneratedKeys = true)
  private Long id;
  private String title;
  private String description;
  private String tag;
  private Long gemCreate;
  private Long gemModified;
  private Long creator;
  private Integer commentCount;
  private Integer viewCount;
  private Integer likeCount;
}
