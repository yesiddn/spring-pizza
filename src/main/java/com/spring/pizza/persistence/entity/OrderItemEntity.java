package com.spring.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@IdClass(OrderItemId.class) // con esta notacion le indicamos a jpa que la clave primaria es compuesta
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity {
  // Se puede crear la clave primaria compuesta con @Embable pero de esta forma permite individualizar cada una de las columnas de las llaves primarias
  @Id
  @Column(nullable = false)
  private String idOrder;

  @Id
  @Column(nullable = false)
  private Integer idItem;

  @Column(nullable = false)
  private Integer idPizza;

  @Column(nullable = false, columnDefinition = "Decimal(2,1)")
  private Double quantity;

  @Column(nullable = false, columnDefinition = "Decimal(5,2)")
  private Double price;
}
