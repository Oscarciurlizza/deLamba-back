package com.app.deLamba.module.order.model;

import com.app.deLamba.module.business.model.Location;

import lombok.Data;

@Data
public class DeliveryInfo {
  private String address;
  private String reference;
  private Location location;
  private String deliveryNotes;
}
