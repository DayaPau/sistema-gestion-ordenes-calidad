# Sistema de Gestión de Órdenes

Proyecto final integrador de **ISWZ3208 - Calidad de Software**.

## Objetivo

Refactorizar un proyecto Java básico aplicando técnicas de calidad de software:

- Clean Code.
- Principios SOLID.
- Pruebas unitarias con JUnit 5.
- Análisis estático con Checkstyle y PMD.
- Cobertura de pruebas con JaCoCo.
- Pipeline CI/CD con GitHub Actions.

## Problema inicial

El proyecto original tenía una sola clase `OrderManager` que mezclaba:

- datos de clientes,
- datos de órdenes,
- lógica de negocio,
- salida por consola.

Además, usaba listas sin genéricos, no validaba datos de entrada y no tenía pruebas automatizadas.

## Solución aplicada

El sistema se separó en paquetes:

```text
src/main/java/com/dayana/calidad/orders
├── app
│   ├── Main.java
│   └── OrderPrinter.java
├── model
│   ├── Customer.java
│   ├── Order.java
│   └── Product.java
├── repository
│   ├── InMemoryOrderRepository.java
│   └── OrderRepository.java
└── service
    └── OrderService.java
```

## Principios aplicados

- **SRP:** cada clase tiene una responsabilidad clara.
- **DIP:** `OrderService` depende de la interfaz `OrderRepository`, no de una clase concreta.
- **Clean Code:** nombres descriptivos, métodos cortos y validaciones explícitas.

## Requisitos

- Java 17.
- Maven 3.9 o superior.
- Git.

## Comandos principales

Compilar y ejecutar pruebas:

```bash
mvn clean test
```

Ejecutar todas las validaciones de calidad:

```bash
mvn clean verify
```

Ejecutar el programa:

```bash
mvn clean compile
java -cp target/classes com.dayana.calidad.orders.app.Main
```

## Reportes generados

Después de ejecutar `mvn clean verify`, revisar:

- JaCoCo: `target/site/jacoco/index.html`
- Checkstyle: `target/checkstyle-result.xml`
- PMD: `target/pmd.xml`
- CPD: `target/cpd.xml`
- Pruebas: `target/surefire-reports`

## Pipeline CI/CD

El pipeline se encuentra en:

```text
.github/workflows/ci.yml
```

Este flujo ejecuta automáticamente:

1. Checkout del repositorio.
2. Configuración de Java 17.
3. `mvn clean verify`.
4. Publicación de reportes como artefactos.

## Comparativa antes y después

| Aspecto | Antes | Después |
|---|---|---|
| Estructura | Una sola clase `OrderManager` | Paquetes `model`, `repository`, `service`, `app` |
| Cohesión | Baja | Alta |
| Nombres | Ambiguos | Descriptivos |
| Validación | No existía | Constructores y servicio validan datos |
| Pruebas | 0 pruebas | Pruebas JUnit 5 para modelo, repositorio y servicio |
| CI/CD | No existía | GitHub Actions activo |
| Calidad | Sin reportes | Checkstyle, PMD y JaCoCo |
