package com.dayana.calidad.orders.model;

import java.util.Objects;

/**
 * Represents the product requested in an order.
 */
public final class Product {

  private final String name;

  /**
   * Creates a product with a valid name.
   *
   * @param name product name
   */
  public Product(final String name) {
    this.name = validateName(name);
  }

  public String getName() {
    return name;
  }

  private String validateName(final String value) {
    if (Objects.isNull(value) || value.isBlank()) {
      throw new IllegalArgumentException("El nombre del producto es obligatorio.");
    }
    return value.trim();
  }
}
