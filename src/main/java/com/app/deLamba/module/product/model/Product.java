package com.app.deLamba.module.product.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.app.deLamba.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "products")
public class Product extends BaseEntity {
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    private String businessId;
    private String productCategoryId;
    private ProductStatus status;
    private List<String> additionalIds;
    private List<String> discountIds;
}
