package com.dayana.calidad.orders.repository;

import com.dayana.calidad.orders.model.Order;
import java.util.List;

/**
 * Defines persistence operations for orders.
 */
public interface OrderRepository {

  /**
   * Saves an order.
   *
   * @param order order to save
   */
  void save(Order order);

  /**
   * Returns all registered orders.
   *
   * @return immutable list of orders
   */
  List<Order> findAll();
}
