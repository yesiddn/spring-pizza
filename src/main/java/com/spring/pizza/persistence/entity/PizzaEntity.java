package com.spring.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "pizza")
@EntityListeners(AuditingEntityListener.class)
@Getter // lombok
@Setter
@NoArgsConstructor // quiere decir que el constructor no llega argumentos
public class PizzaEntity extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_pizza")
  // Spring hace la conversion automatica a snake_case, por lo que el nombre de la columna no es necesario PERO HAY QUE TENER CUIDADO, LOS @Join GENERAN PROBLEMAS
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

//  Se pueden dejar aqui estas columnas, pero como es informacion que no es relevante para el usuario es mejor llevarlas a una superclase
//  @Column(name = "created_at", updatable = false)
//  @CreatedDate
//  private LocalDateTime createdAt;
//
//  @Column(name = "updated_at")
//  @LastModifiedDate
//  private LocalDateTime updatedAt;
}
