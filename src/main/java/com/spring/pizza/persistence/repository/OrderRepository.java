package com.spring.pizza.persistence.repository;

import com.spring.pizza.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, String> {
  List<OrderEntity> findAllByDateAfter(LocalDateTime date); // after o before

  List<OrderEntity> findAllByMethodIn(List<String> methods);

  @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true)
    // con esto spring data sabra que no tiene que traducir nada sino que la query ya viene lista para enviar a la base de datos
  List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);
}
