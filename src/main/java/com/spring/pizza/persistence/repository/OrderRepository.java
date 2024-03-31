package com.spring.pizza.persistence.repository;

import com.spring.pizza.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends ListCrudRepository<OrderEntity, String> {
}
