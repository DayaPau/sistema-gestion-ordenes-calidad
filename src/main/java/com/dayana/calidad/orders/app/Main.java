package com.dayana.calidad.orders.app;

import com.dayana.calidad.orders.repository.InMemoryOrderRepository;
import com.dayana.calidad.orders.repository.OrderRepository;
import com.dayana.calidad.orders.service.OrderService;

/**
 * Application entry point.
 */
public final class Main {

  private Main() {
    throw new UnsupportedOperationException("Clase de utilidad.");
  }

  /**
   * Runs a small demonstration of the order management system.
   *
   * @param args command-line arguments
   */
  public static void main(final String[] args) {
    final OrderRepository orderRepository = new InMemoryOrderRepository();
    final OrderService orderService = new OrderService(orderRepository);
    final OrderPrinter orderPrinter = new OrderPrinter(System.out);

    orderService.registerOrder("Alice", "Laptop");
    orderService.registerOrder("Bob", "Phone");

    orderPrinter.printOrders(orderService.getRegisteredOrders());
  }
}
