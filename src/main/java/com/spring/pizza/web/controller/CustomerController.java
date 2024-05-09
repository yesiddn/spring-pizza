package com.spring.pizza.web.controller;

import com.spring.pizza.persistence.entity.CustomerEntity;
import com.spring.pizza.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
  private final CustomerService customerService;

  @Autowired // no es necesario pero es bueno para saber que le estamos delegando esto a Spring
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/phone/{phone}")
  public ResponseEntity<CustomerEntity> getByPhone(@PathVariable String phone) {
    return ResponseEntity.ok(this.customerService.findByPhone(phone));
  }
}
