package com.spring.pizza.services;

import com.spring.pizza.persistence.entity.OrderEntity;
import com.spring.pizza.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
  private final OrderRepository orderRepository; // con es final se obliga a que el repository se pase por el constructor

  @Autowired
  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public List<OrderEntity> getAll() {
    return this.orderRepository.findAll();
  }
}
