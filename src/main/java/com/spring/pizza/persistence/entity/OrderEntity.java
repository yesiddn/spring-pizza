package com.spring.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

  @Column(name = "additional_notes", length = 200)
  private String additionalNotes;

  //  Aqui tampoco es necesario crear la relacion en customer para ver las ordenes que tiene porque mas adelante se puede realizar esto usando consultas, method queries o los spring repositories
  @OneToOne
  @JoinColumn(name = "id_customer", referencedColumnName = "id_customer", insertable = false, updatable = false)
  private CustomerEntity customer;

  @OneToMany(mappedBy = "order")
  private List<OrderItemEntity> items;
}
