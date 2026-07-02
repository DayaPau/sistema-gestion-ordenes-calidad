package com.dayana.calidad.orders.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a registered order.
 */
public final class Order {

  private final UUID id;
  private final Customer customer;
  private final Product product;
  private final LocalDateTime createdAt;

  /**
   * Creates a new order for a customer and a product.
   *
   * @param customer customer who places the order
   * @param product ordered product
   */
  public Order(final Customer customer, final Product product) {
    this(UUID.randomUUID(), customer, product, LocalDateTime.now());
  }

  /**
   * Creates an order with explicit values, useful for tests.
   *
   * @param id order identifier
   * @param customer customer who places the order
   * @param product ordered product
   * @param createdAt creation date and time
   */
  public Order(
      final UUID id,
      final Customer customer,
      final Product product,
      final LocalDateTime createdAt) {
    this.id = Objects.requireNonNull(id, "El identificador de la orden es obligatorio.");
    this.customer = Objects.requireNonNull(customer, "El cliente es obligatorio.");
    this.product = Objects.requireNonNull(product, "El producto es obligatorio.");
    this.createdAt = Objects.requireNonNull(createdAt, "La fecha de creación es obligatoria.");
  }

  public UUID getId() {
    return id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public Product getProduct() {
    return product;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
}
