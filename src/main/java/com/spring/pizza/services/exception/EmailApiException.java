package com.spring.pizza.services.exception;

public class EmailApiException extends RuntimeException {
  public EmailApiException() {
    super("Error sending email...");
  }
}
