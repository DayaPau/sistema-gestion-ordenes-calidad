package com.dayana.calidad.orders.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class OrderTest {

  @Test
  void shouldCreateOrderWithCustomerAndProduct() {
    final Customer customer = new Customer("Alice");
    final Product product = new Product("Laptop");

    final Order order = new Order(customer, product);

    assertNotNull(order.getId());
    assertEquals(customer, order.getCustomer());
    assertEquals(product, order.getProduct());
    assertNotNull(order.getCreatedAt());
  }

  @Test
  void shouldCreateOrderWithExplicitValues() {
    final UUID id = UUID.randomUUID();
    final Customer customer = new Customer("Alice");
    final Product product = new Product("Laptop");
    final LocalDateTime creationDate = LocalDateTime.of(2026, 7, 2, 10, 0);

    final Order order = new Order(id, customer, product, creationDate);

    assertEquals(id, order.getId());
    assertEquals(creationDate, order.getCreatedAt());
  }

  @Test
  void shouldRejectNullCustomer() {
    final Product product = new Product("Laptop");

    assertThrows(NullPointerException.class, () -> new Order(null, product));
  }

  @Test
  void shouldRejectNullProduct() {
    final Customer customer = new Customer("Alice");

    assertThrows(NullPointerException.class, () -> new Order(customer, null));
  }
}
