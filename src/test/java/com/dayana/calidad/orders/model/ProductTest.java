package com.dayana.calidad.orders.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ProductTest {

  @Test
  void shouldCreateProductWithValidName() {
    final Product product = new Product("Laptop");

    assertEquals("Laptop", product.getName());
  }

  @Test
  void shouldRejectNullProductName() {
    assertThrows(IllegalArgumentException.class, () -> new Product(null));
  }

  @Test
  void shouldRejectBlankProductName() {
    assertThrows(IllegalArgumentException.class, () -> new Product("   "));
  }
}
