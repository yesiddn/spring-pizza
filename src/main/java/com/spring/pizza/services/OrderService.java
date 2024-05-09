package com.spring.pizza.services;

import com.spring.pizza.persistence.entity.OrderEntity;
import com.spring.pizza.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
  private final OrderRepository orderRepository; // con es final se obliga a que el repository se pase por el constructor

  private static final String DELIVERY = "D";
  private static final String CARRYOUT = "C";
  private static final String ON_SITE = "S";

  @Autowired
  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public List<OrderEntity> getAll() {
    return this.orderRepository.findAll();
  }

  public List<OrderEntity> getTodayOrders() {
    LocalDateTime today = LocalDate.now().atTime(0, 0); // regresa la fecha y la hora la inicializa en 00:00
    return this.orderRepository.findAllByDateAfter(today);
  }

  public List<OrderEntity> getOutsideOrders() {
    List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
    return this.orderRepository.findAllByMethodIn(methods);
  }

  public List<OrderEntity> getCustomerOrders(String idCustomer) {
    return this.orderRepository.findCustomerOrders(idCustomer);
  }
}
