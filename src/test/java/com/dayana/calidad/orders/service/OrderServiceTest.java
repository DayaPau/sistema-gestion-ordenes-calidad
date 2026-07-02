package com.dayana.calidad.orders.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.dayana.calidad.orders.model.Order;
import com.dayana.calidad.orders.repository.InMemoryOrderRepository;
import com.dayana.calidad.orders.repository.OrderRepository;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

  @Test
  void shouldRegisterOrder() {
    final OrderRepository repository = new InMemoryOrderRepository();
    final OrderService service = new OrderService(repository);

    final Order order = service.registerOrder("Alice", "Laptop");

    assertEquals("Alice", order.getCustomer().getName());
    assertEquals("Laptop", order.getProduct().getName());
    assertEquals(1, service.getRegisteredOrders().size());
  }

  @Test
  void shouldListRegisteredOrders() {
    final OrderRepository repository = new InMemoryOrderRepository();
    final OrderService service = new OrderService(repository);

    service.registerOrder("Alice", "Laptop");
    service.registerOrder("Bob", "Phone");

    assertEquals(2, service.getRegisteredOrders().size());
    assertFalse(service.getRegisteredOrders().isEmpty());
  }

  @Test
  void shouldRejectInvalidCustomerDuringRegistration() {
    final OrderService service = new OrderService(new InMemoryOrderRepository());

    assertThrows(IllegalArgumentException.class, () -> service.registerOrder("", "Laptop"));
  }

  @Test
  void shouldRejectInvalidProductDuringRegistration() {
    final OrderService service = new OrderService(new InMemoryOrderRepository());

    assertThrows(IllegalArgumentException.class, () -> service.registerOrder("Alice", ""));
  }
}
