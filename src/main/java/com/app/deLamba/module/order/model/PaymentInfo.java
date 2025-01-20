package com.app.deLamba.module.order.model;

import lombok.Data;

@Data
public class PaymentInfo {
  private PaymentMethod method;
  private PaymentStatus status;
  private String transactionId;
}
