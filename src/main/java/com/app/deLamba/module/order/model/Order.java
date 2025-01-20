package com.app.deLamba.module.order.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.app.deLamba.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "orders")
public class Order extends BaseEntity {
  private String userId;
  private OrderStatus status;
  private BigDecimal shippingCost;
  private BigDecimal subtotal;
  private BigDecimal total;
  private List<OrderDetail> orderDetails;
  private DeliveryInfo deliveryInfo;
  private PaymentInfo paymentInfo;
}
