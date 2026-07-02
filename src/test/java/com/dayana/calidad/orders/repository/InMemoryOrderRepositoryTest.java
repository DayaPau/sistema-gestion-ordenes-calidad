package com.dayana.calidad.orders.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dayana.calidad.orders.model.Customer;
import com.dayana.calidad.orders.model.Order;
import com.dayana.calidad.orders.model.Product;
import org.junit.jupiter.api.Test;

class InMemoryOrderRepositoryTest {

  @Test
  void shouldStartWithEmptyList() {
    final OrderRepository repository = new InMemoryOrderRepository();

    assertTrue(repository.findAll().isEmpty());
  }

  @Test
  void shouldSaveOrder() {
    final OrderRepository repository = new InMemoryOrderRepository();
    final Order order = createOrder();

    repository.save(order);

    assertEquals(1, repository.findAll().size());
    assertEquals(order, repository.findAll().get(0));
  }

  @Test
  void shouldReturnUnmodifiableList() {
    final OrderRepository repository = new InMemoryOrderRepository();

    assertThrows(UnsupportedOperationException.class, () -> repository.findAll().add(createOrder()));
  }

  private Order createOrder() {
    return new Order(new Customer("Alice"), new Product("Laptop"));
  }
}
