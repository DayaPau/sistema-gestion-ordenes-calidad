package com.dayana.calidad.orders.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CustomerTest {

  @Test
  void shouldCreateCustomerWithValidName() {
    final Customer customer = new Customer("Alice");

    assertEquals("Alice", customer.getName());
  }

  @Test
  void shouldTrimCustomerName() {
    final Customer customer = new Customer("  Alice  ");

    assertEquals("Alice", customer.getName());
  }

  @Test
  void shouldRejectNullCustomerName() {
    assertThrows(IllegalArgumentException.class, () -> new Customer(null));
  }

  @Test
  void shouldRejectBlankCustomerName() {
    assertThrows(IllegalArgumentException.class, () -> new Customer("   "));
  }
}
