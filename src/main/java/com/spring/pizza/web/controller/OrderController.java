package com.spring.pizza.web.controller;

import com.spring.pizza.persistence.entity.OrderEntity;
import com.spring.pizza.persistence.projection.OrderSummary;
import com.spring.pizza.services.OrderService;
import com.spring.pizza.services.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
  private final OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  // generara un error porque hay un llamado infinito en las relaciones
  // en OrderEntity se hace una relacion con  OrderItemEntity que a su vez tiene una relacion con OrderEntity, lo que genera un bucle infinito
  // para evitar esto se usa @JsonIgnore en OrderItemEntity, tambien se puede evitar con un DTO
  @GetMapping
  public ResponseEntity<List<OrderEntity>> getAll() {
    return ResponseEntity.ok(this.orderService.getAll());
  }

  @GetMapping("/today")
  public ResponseEntity<List<OrderEntity>> getTodayOrders() {
    return ResponseEntity.ok(this.orderService.getTodayOrders());
  }

  @GetMapping("/outside")
  public ResponseEntity<List<OrderEntity>> getOutsideOrders() {
    return ResponseEntity.ok(this.orderService.getOutsideOrders());
  }

  @GetMapping("/customer/{id}")
  public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String id) {
    return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
  }

  @GetMapping("/summary/{id}")
  public ResponseEntity<OrderSummary> getOrderSummary(@PathVariable int id) {
    return ResponseEntity.ok(this.orderService.getOrderSummary(id));
  }

  @PostMapping("/random")
  public ResponseEntity<Boolean> randomOrder(@RequestBody RandomOrderDto randomOrder) {
    return ResponseEntity.ok(this.orderService.saveRandomOrder(randomOrder));
  }
}
