package com.dayana.calidad.orders.app;

import com.dayana.calidad.orders.model.Order;
import java.io.PrintStream;
import java.util.List;
import java.util.Objects;

/**
 * Prints orders in the console.
 */
public final class OrderPrinter {

  private final PrintStream output;

  /**
   * Creates a printer with a specific output stream.
   *
   * @param output destination for messages
   */
  public OrderPrinter(final PrintStream output) {
    this.output = Objects.requireNonNull(output, "La salida por consola es obligatoria.");
  }

  /**
   * Prints the registered orders.
   *
   * @param orders orders to print
   */
  public void printOrders(final List<Order> orders) {
    if (orders.isEmpty()) {
      output.println("No existen órdenes registradas.");
      return;
    }

    orders.forEach(order -> output.println(formatOrder(order)));
  }

  private String formatOrder(final Order order) {
    return "Cliente: " + order.getCustomer().getName()
        + " | Producto: " + order.getProduct().getName()
        + " | Codigo: " + order.getId();
  }
}
