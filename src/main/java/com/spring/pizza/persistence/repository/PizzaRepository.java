package com.spring.pizza.persistence.repository;

import com.spring.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> { // La diferencia entre ListCrudRepository y CrudRepository es que el primero nos devuelve un List, mientras que, el sugundo, nos devuelve un Iterable.
  //  Query methods -> https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
  List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

  Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String pizzaName); // con ignoreCase se evita el uso de mayusculas y minusculas | con First y Top obtenemos el primer elemento

  List<PizzaEntity> findByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

  List<PizzaEntity> findByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
  List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);
  int countByVeganTrue();
}
