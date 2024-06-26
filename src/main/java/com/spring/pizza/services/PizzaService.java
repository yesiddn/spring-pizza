package com.spring.pizza.services;

import com.spring.pizza.persistence.entity.PizzaEntity;
import com.spring.pizza.persistence.repository.PizzaRepository;
import com.spring.pizza.services.dto.UpdatePizzaPriceDto;
import com.spring.pizza.services.exception.EmailApiException;
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

  @Transactional(noRollbackFor = EmailApiException.class)
  // con esta anotacion nos aseguramos de que en caso de existir dos o mas modificaciones a la base de datos u otras acciones se ejecuten todas o ninguna
  // en algunas tareas, no es necesario hacer rollback ya que no dependen una de la otra, por ello @transactional recibe un argumento
  // noRollBackFor cuando no queremos hacer rollback
  // rollBackFor cuando queremos hacer rollback de algo en especifico
  // propagation = Propagation.REQUIRED cuando no existe una transaccion la crea -> https://www.baeldung.com/spring-transactional-propagation-isolation
  public void updatePrice(UpdatePizzaPriceDto dto) {
    this.pizzaRepository.updatePrice(dto);
    this.sendEmail();
  }

  private void sendEmail() {
    throw new EmailApiException();
  }

  public boolean exists(int idPizza) {
    return this.pizzaRepository.existsById(idPizza);
  }
}
