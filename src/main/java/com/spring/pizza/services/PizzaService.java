package com.spring.pizza.services;

import com.spring.pizza.persistence.entity.PizzaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PizzaService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<PizzaEntity> getAll() {
    return this.jdbcTemplate.query("SELECT * FROM pizza WHERE available = 0", new BeanPropertyRowMapper<>(PizzaEntity.class)); // puede ser bastante útil para una consulta rápida o de prueba evitandonos construir un repositorio o entity manager
  }
}
