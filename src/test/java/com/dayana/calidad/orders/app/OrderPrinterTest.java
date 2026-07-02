package com.dayana.calidad.orders.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dayana.calidad.orders.model.Customer;
import com.dayana.calidad.orders.model.Order;
import com.dayana.calidad.orders.model.Product;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrderPrinterTest {

  @Test
  void shouldPrintMessageWhenOrderListIsEmpty() {
    final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
    final OrderPrinter orderPrinter = new OrderPrinter(new PrintStream(outputContent));

    orderPrinter.printOrders(List.of());

    final String result = outputContent.toString();

    assertTrue(result.contains("No existen órdenes registradas."));
  }

  @Test
  void shouldPrintRegisteredOrders() {
    final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
    final OrderPrinter orderPrinter = new OrderPrinter(new PrintStream(outputContent));
    final Order order = new Order(new Customer("Alice"), new Product("Laptop"));

    orderPrinter.printOrders(List.of(order));

    final String result = outputContent.toString();

    assertTrue(result.contains("Cliente: Alice"));
    assertTrue(result.contains("Producto: Laptop"));
    assertTrue(result.contains("Codigo:"));
  }
}