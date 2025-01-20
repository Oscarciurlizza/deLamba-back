package com.app.deLamba.module.business.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.app.deLamba.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "business_categories")
public class BusinessCategory extends BaseEntity {
  private String name;
  private String description;
  private String icon;
  private String slug;
}
