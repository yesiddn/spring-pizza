package com.spring.pizza.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemId implements Serializable {
  private Integer idOrder;
  private Integer idItem;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OrderItemId that)) return false;
    return Objects.equals(idOrder, that.idOrder) && Objects.equals(idItem, that.idItem);
  } // se puede crear usando el wizard de intellij -> se escribe equals (opcion wizard) -> check accept subclases to ...

  @Override
  public int hashCode() {
    return Objects.hash(idOrder, idItem);
  }
}
