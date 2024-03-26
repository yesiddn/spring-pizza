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
  @Column(name = "id_order", nullable = false)
  private String idOrder;

  @Id
  @Column(nullable = false)
  private Integer idItem;

  @Column(name = "id_pizza", nullable = false)
  private Integer idPizza;

  @Column(nullable = false, columnDefinition = "Decimal(2,1)")
  private Double quantity;

  @Column(nullable = false, columnDefinition = "Decimal(5,2)")
  private Double price;

  //  No se crea la relacion en pizza porque no es necesario en una pizza a que order pertenece
  @OneToOne
  @JoinColumn(name = "id_pizza", referencedColumnName = "id_pizza", insertable = false, updatable = false)
  private PizzaEntity pizza;

  @ManyToOne
  @JoinColumn(name = "id_order", referencedColumnName = "id_order", insertable = false, updatable = false)
  private OrderEntity order;
}
