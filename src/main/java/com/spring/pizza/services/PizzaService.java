package com.spring.pizza.services;

import com.spring.pizza.persistence.entity.PizzaEntity;
import com.spring.pizza.persistence.repository.PizzaRepository;
import com.spring.pizza.services.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {
//  private final JdbcTemplate jdbcTemplate;

//  @Autowired
//  public PizzaService(JdbcTemplate jdbcTemplate) {
//    this.jdbcTemplate = jdbcTemplate;
//  }
//
//  public List<PizzaEntity> getAll() {
//    return this.jdbcTemplate.query("SELECT * FROM pizza WHERE available = 0", new BeanPropertyRowMapper<>(PizzaEntity.class)); // puede ser bastante útil para una consulta rápida o de prueba evitandonos construir un repositorio o entity manager
//  }

  private final PizzaRepository pizzaRepository;

  @Autowired
  public PizzaService(PizzaRepository pizzaRepository) {
    this.pizzaRepository = pizzaRepository;
  }

  public List<PizzaEntity> getAll() {
    return this.pizzaRepository.findAll();
  }

  public List<PizzaEntity> getAvailable() {
    System.out.println(this.pizzaRepository.countByVeganTrue());
    return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
  }

  public PizzaEntity getByName(String name) {
    return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElse(null); // orElseThrow para mostrar un error en consola
  }

  public List<PizzaEntity> getWith(String ingredient) {
    return this.pizzaRepository.findByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
  }

  public List<PizzaEntity> getWithout(String ingredient) {
    return this.pizzaRepository.findByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
  }

  public List<PizzaEntity> getCheapest(double price) {
    return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
  }

  public PizzaEntity get(int idPizza) {
    return this.pizzaRepository.findById(idPizza).orElse(null);
  }

  public PizzaEntity save(PizzaEntity pizza) {
    return this.pizzaRepository.save(pizza);
  }

  public void delete(int idPizza) {
    this.pizzaRepository.deleteById(idPizza);
  }

  @Transactional
  public void updatePrice(UpdatePizzaPriceDto dto) {
    this.pizzaRepository.updatePrice(dto);
  }

  public boolean exists(int idPizza) {
    return this.pizzaRepository.existsById(idPizza);
  }
}
