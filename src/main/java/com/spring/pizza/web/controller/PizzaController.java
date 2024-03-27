package com.spring.pizza.web.controller;

import com.spring.pizza.persistence.entity.PizzaEntity;
import com.spring.pizza.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
  private final PizzaService pizzaService;

  @Autowired
  public PizzaController(PizzaService pizzaService) {
    this.pizzaService = pizzaService;
  }

  @GetMapping
  public ResponseEntity<List<PizzaEntity>> getAll() {
    return ResponseEntity.ok(this.pizzaService.getAll());
  }
}
