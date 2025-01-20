package com.app.deLamba.module.product.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.app.deLamba.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "product_categories")
public class ProductCategory extends BaseEntity {
  private String name;
  private String description;
  private String businessId;
}
