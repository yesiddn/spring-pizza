package com.spring.pizza.persistence.audit;

import com.spring.pizza.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

// las auditorias solo se realizaran cuando se pase por el proceso de vida de un entity de jpa, es decir, solo cuando los metodos de jpaRepository (save, delete, find) se ejecuten
public class AuditPizzaListener {
  private PizzaEntity currenValue;

  @PostLoad
  public void postLoad(PizzaEntity pizzaEntity) {
    System.out.println("POST LOAD");
    this.currenValue = SerializationUtils.clone(pizzaEntity); // si no se clona, para los siguientes metodos, y como funciona la memoria en java, el objeto se sobrecargaria en memoria y no se verian los cambios entre metodos adecuadamente
  }

  @PostPersist
  @PostUpdate
//  se pueden aplicar dos anotaciones (o mas, no lo se), la primera para la creacion de una nueva pizza y la segunda para la actualizacion de una pizza
  public void onPostPersist(PizzaEntity entity) {
    System.out.println("POST PERSIST OR UPDATE");
    System.out.println("OLD VALUE: " + this.currenValue);
    System.out.println("NEW VALUE: " + entity.toString());
  }

  @PreRemove
//  este metodo se ejecutara antes de ejecutar el proceso de eliminar una pizza
  public void onPreDelete(PizzaEntity entity) {
    System.out.println(entity.toString());
  }

//  pre para ejecutarse antes y post para ejecutarse despues
}
