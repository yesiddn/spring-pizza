package com.spring.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pizza")
@Getter // lombok
@Setter
@NoArgsConstructor // quiere decir que el constructor no llega argumentos
public class PizzaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_pizza")
  // Spring hace la conversion automatica a snake_case, por lo que el nombre de la columna no es necesario
  private Integer idPizza;

  @Column(nullable = false, length = 30, unique = true)
  private String name;

  @Column(nullable = false, length = 150)
  private String description;

  @Column(nullable = false, columnDefinition = "Decimal(5,2)") // columnDefinition es para asignar una longitud maxima
  private Double price;

  @Column(columnDefinition = "TINYINT") // cambia para postgres -> smallint
  private Boolean vegetarian;

  @Column(columnDefinition = "TINYINT")
  private Boolean vegan;

  @Column(columnDefinition = "TINYINT", nullable = false)
  private Boolean available;
}
