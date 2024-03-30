package com.spring.pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
// No es estrictamente necesario agregar la anotación @EnableJpaRepositories en Spring Boot, ya que Spring Boot detecta automáticamente las interfaces que extienden JpaRepository y las habilita de forma predeterminada. Sin embargo, si deseas personalizar la configuración de tus repositorios JPA, puedes agregar dicha anotación para especificar la ubicación base de los repositorios o para habilitar características adicionales.
public class SpringPizzaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringPizzaApplication.class, args);
  }

}
