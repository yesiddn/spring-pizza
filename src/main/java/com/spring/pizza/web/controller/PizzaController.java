package com.spring.pizza.web.controller;

import ch.qos.logback.classic.Logger;
import com.spring.pizza.persistence.entity.PizzaEntity;
import com.spring.pizza.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
  private final PizzaService pizzaService;
  private Logger log;

  @Autowired
  public PizzaController(PizzaService pizzaService) {
    this.pizzaService = pizzaService;
  }

  @GetMapping
  public ResponseEntity<List<PizzaEntity>> getAll() {
    return ResponseEntity.ok(this.pizzaService.getAll());
  }

  @GetMapping("/{idPizza}")
  public ResponseEntity<PizzaEntity> get(@PathVariable int idPizza) {
    return ResponseEntity.ok(this.pizzaService.get(idPizza));
  }

  @GetMapping("/available")
  public ResponseEntity<List<PizzaEntity>> getAvailable() {
    return ResponseEntity.ok(this.pizzaService.getAvailable());
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<PizzaEntity> getByName(@PathVariable String name) {
    return ResponseEntity.ok(this.pizzaService.getByName(name));
  }

  @GetMapping("/with/{ingredient}")
  public ResponseEntity<List<PizzaEntity>> getWith(@PathVariable String ingredient) {
    return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
  }

  @GetMapping("/without/{ingredient}")
  public ResponseEntity<List<PizzaEntity>> getWithout(@PathVariable String ingredient) {
    return ResponseEntity.ok(this.pizzaService.getWithout(ingredient));
  }

  //Una vez creado, si le enviamos el objeto de nuevo pero con su respectivo id (13) jpa hace un select para validar la informacion que viene y,  en caso de que haya cambiado, hara un update, de lo contrario, no hara nada. Para hacer mas correcta la consulta, se hace un metodo PUT para editar
  @PostMapping
  public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizza) {
    if (pizza.getIdPizza() == null || !this.pizzaService.exists(pizza.getIdPizza())) {
      return ResponseEntity.ok(this.pizzaService.save(pizza));
    }

    return ResponseEntity.badRequest().build();
  }

  @PutMapping
  public ResponseEntity<?> update(@RequestBody PizzaEntity pizza) {
    try {
      if (pizza.getIdPizza() != null && this.pizzaService.exists(pizza.getIdPizza())) {
        return ResponseEntity.ok(this.pizzaService.save(pizza));
      }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Pizza no Existe!");
    } catch (Exception e) {
      log.error(e.getMessage());
      return ResponseEntity.internalServerError().build();
    }
  }

  @DeleteMapping("/{idPizza}")
  public ResponseEntity<?> delete(@PathVariable int idPizza) {
    try {
      if (this.pizzaService.exists(idPizza)) {
        this.pizzaService.delete(idPizza);
        return ResponseEntity.ok().build();
      }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Pizza no Existe!");
    } catch (Exception e) {
      log.error(e.getMessage());
      return ResponseEntity.internalServerError().build();
    }
  }
}
