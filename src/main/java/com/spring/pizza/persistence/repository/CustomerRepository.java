package com.spring.pizza.persistence.repository;

import com.spring.pizza.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity, String> {
  //  con JPQL se usan entities en lugar de tablas, ya que este hace la transformacion automaticamente
  @Query(value = "SELECT c FROM CustomerEntity c WHERE c.phoneNumber = :phone")
  CustomerEntity findByPhone(@Param("phone") String phone);
}
