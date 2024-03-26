package com.spring.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id_order", nullable = false)
  private String idOrder;

  @Column(name = "id_customer", nullable = false) // , length = 15
  private String idCustomer;

  @Column(nullable = false, columnDefinition = "DATETIME") // para postgres cambia -> tymestamp
  private LocalDateTime date;

  @Column(nullable = false, columnDefinition = "DECIMAL(6, 2)")
  private Double total;

  @Column(nullable = false, columnDefinition = "CHAR(1)")
  private String method;

  @Column(name = "addtional_notes", length = 200)
  private String additionalNotes;
}
