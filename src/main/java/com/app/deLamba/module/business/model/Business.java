package com.app.deLamba.module.business.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.app.deLamba.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "businesses")
public class Business extends BaseEntity {
  private String name;
  private String description;
  private String address;
  private String phone;
  private String schedule;
  private String userId;
  private String businessCategoryId;
  private BusinessStatus status;
  private Location location;
}
