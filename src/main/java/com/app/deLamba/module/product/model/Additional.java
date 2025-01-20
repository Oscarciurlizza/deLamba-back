package com.app.deLamba.module.product.model;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

import com.app.deLamba.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "additionals")
public class Additional extends BaseEntity {
    private String name;
    private BigDecimal price;
    private Boolean available;
    private String productId;
}
