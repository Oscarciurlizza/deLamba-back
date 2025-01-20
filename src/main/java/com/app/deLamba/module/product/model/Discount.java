package com.app.deLamba.module.product.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import com.app.deLamba.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "discounts")
public class Discount extends BaseEntity {
    private BigDecimal percentage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String productId;
    private DiscountType type;
}
