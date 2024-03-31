package com.spring.pizza.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
  @OneToOne(fetch = FetchType.LAZY)
  // con lazy no se carga la relacion hasta que se usa, si no se usa no se cargaran los datos. Cuando se usa la relacion? Cuando se usa el metodo getCustomer.name y con un @JsonIgnore no se llamara la relacion
  @JoinColumn(name = "id_customer", referencedColumnName = "id_customer", insertable = false, updatable = false)
  @JsonIgnore
  private CustomerEntity customer;

  @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
  // con eager cuando se trate de recuperar OrderEntity, automaticamente traera la relacion
  private List<OrderItemEntity> items;

//  Para las relaciones OneToMany y ManyToMany se tienen el valor por default de LAZY y para ManyToOne y OneToOne se usa el valor EAGER
}
