package com.bilibili.majiang.demo.model;

import lombok.Data;

@Data
public class Publish {

  private long id;
  private String title;
  private String description;
  private String tag;
  private long gemCreate;
  private long gemModified;
  private long creator;
  private long commentCount;
  private long viewCount;
  private long likeCount;
}
