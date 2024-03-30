package com.spring.pizza.persistence.repository;

import com.spring.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> { // La diferencia entre ListCrudRepository y CrudRepository es que el primero nos devuelve un List, mientras que, el sugundo, nos devuelve un Iterable.
}
