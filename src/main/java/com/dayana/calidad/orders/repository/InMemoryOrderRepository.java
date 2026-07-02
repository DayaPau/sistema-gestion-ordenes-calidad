package com.dayana.calidad.orders.repository;

import com.dayana.calidad.orders.model.Order;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Stores orders in memory for this academic example.
 */
public final class InMemoryOrderRepository implements OrderRepository {

  private final List<Order> orders = new ArrayList<>();

  @Override
  public void save(final Order order) {
    orders.add(Objects.requireNonNull(order, "La orden es obligatoria."));
  }

  @Override
  public List<Order> findAll() {
    return Collections.unmodifiableList(orders);
  }
}
