package com.bilibili.majiang.demo.model;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

@Data
public class Notification {
  @Id
  @KeySql(useGeneratedKeys = true)
  private Long id;
  private Long notifier;
  private Long receiver;
  private Integer type;
  private Long gemCreate;
  private Integer status;
  private Long outerid;

}
