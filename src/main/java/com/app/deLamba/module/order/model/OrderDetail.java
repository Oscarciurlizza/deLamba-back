package com.app.deLamba.module.order.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class OrderDetail {
    private String productId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
    private List<String> additionalIds;
    private List<String> discountIds;
}
