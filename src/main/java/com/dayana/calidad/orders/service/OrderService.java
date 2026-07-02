package com.dayana.calidad.orders.service;

import com.dayana.calidad.orders.model.Customer;
import com.dayana.calidad.orders.model.Order;
import com.dayana.calidad.orders.model.Product;
import com.dayana.calidad.orders.repository.OrderRepository;
import java.util.List;
import java.util.Objects;

/**
 * Contains the business logic for order management.
 */
public final class OrderService {

  private final OrderRepository orderRepository;

  /**
   * Creates the service using a repository abstraction.
   *
   * @param orderRepository repository used to store orders
   */
  public OrderService(final OrderRepository orderRepository) {
    this.orderRepository = Objects.requireNonNull(
        orderRepository,
        "El repositorio de órdenes es obligatorio.");
  }

  /**
   * Registers an order for a customer and a product.
   *
   * @param customerName customer name
   * @param productName product name
   * @return registered order
   */
  public Order registerOrder(final String customerName, final String productName) {
    final Order order = new Order(new Customer(customerName), new Product(productName));
    orderRepository.save(order);
    return order;
  }

  /**
   * Gets all registered orders.
   *
   * @return immutable list of orders
   */
  public List<Order> getRegisteredOrders() {
    return orderRepository.findAll();
  }
}
