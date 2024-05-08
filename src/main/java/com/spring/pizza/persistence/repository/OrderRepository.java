package com.spring.pizza.persistence.repository;

import com.spring.pizza.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, String> {
  List<OrderEntity> findAllByDateAfter(LocalDateTime date); // after o before
  List<OrderEntity> findAllByMethodIn(List<String> methods);
}
