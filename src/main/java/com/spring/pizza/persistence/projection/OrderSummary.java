package com.spring.pizza.persistence.projection;

import java.time.LocalDateTime;

public interface OrderSummary {
  Integer getIdOrder();

  String getCustomerName();

  LocalDateTime getOrderDate();

  Double getOrderTotal();

  String getPizzaNames();
}
