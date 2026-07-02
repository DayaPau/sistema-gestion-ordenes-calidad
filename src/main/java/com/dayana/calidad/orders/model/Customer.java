package com.dayana.calidad.orders.model;

import java.util.Objects;

/**
 * Represents the customer associated with an order.
 */
public final class Customer {

  private final String name;

  /**
   * Creates a customer with a valid name.
   *
   * @param name customer name
   */
  public Customer(final String name) {
    this.name = validateName(name);
  }

  public String getName() {
    return name;
  }

  private String validateName(final String value) {
    if (Objects.isNull(value) || value.isBlank()) {
      throw new IllegalArgumentException("El nombre del cliente es obligatorio.");
    }
    return value.trim();
  }
}
