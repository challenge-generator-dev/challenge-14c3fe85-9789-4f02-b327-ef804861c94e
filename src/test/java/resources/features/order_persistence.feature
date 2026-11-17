# language: es
Característica: Persistencia de Pedidos en DynamoDB
  Como automatizador de pruebas
  Necesito validar que el sistema persiste correctamente los pedidos en DynamoDB
  Para garantizar la integridad de los datos del sistema de gestión de pedidos

  Antecedentes:
    Dado que el sistema está configurado para conectar con DynamoDB local
    Y la tabla de pedidos está disponible en el endpoint configurado

  @escritura @integration
  Escenario: Escribir un nuevo pedido en DynamoDB
    Cuando escribo un pedido con los siguientes datos:
      | campo          | valor                           |
      | orderId        | ORD-2024-001                   |
      | customerId     | CUST-001                       |
      | productId      | PROD-001                       |
      | quantity       | 5                              |
      | totalAmount    | 150.00                         |
      | status         | PENDING                        |
      | createdAt      | 2024-01-15T10:30:00Z           |
    Entonces el pedido debe ser almacenado exitosamente en DynamoDB
    Y debo poder recuperar el pedido por su identificador "ORD-2024-001"
    Y los datos del pedido recuperado deben coincidir con los datos escritos

  @escritura @integration
  Escenario: Actualizar el estado de un pedido existente
    Dado que existe un pedido con identificador "ORD-2024-002" en DynamoDB
      | orderId        | ORD-2024-002                   |
      | customerId     | CUST-002                       |
      | productId      | PROD-002                       |
      | quantity       | 3                              |
      | totalAmount    | 90.00                          |
      | status         | PENDING                        |
      | createdAt      | 2024-01-15T11:00:00Z           |
    Cuando actualizo el estado del pedido "ORD-2024-002" a "COMPLETED"
    Y espero 2 segundos para que la actualización se procese
    Entonces el pedido debe reflejar el nuevo estado "COMPLETED"

  @lectura @integration
  Escenario: Leer un pedido existente desde DynamoDB
    Dado que existe un pedido con identificador "ORD-2024-003" en DynamoDB
      | orderId        | ORD-2024-003                   |
      | customerId     | CUST-003                       |
      | productId      | PROD-003                       |
      | quantity       | 10                             |
      | totalAmount    | 300.00                         |
      | status         | PROCESSING                     |
      | createdAt      | 2024-01-15T12:00:00Z           |
    Cuando recupero el pedido por su identificador "ORD-2024-003"
    Entonces debo obtener los datos completos del pedido
    Y el campo customerId debe ser "CUST-003"
    Y el campo productId debe ser "PROD-003"
    Y el campo quantity debe ser 10
    Y el campo totalAmount debe ser 300.00
    Y el campo status debe ser "PROCESSING"

  @lectura @integration
  Escenario: Verificar que un pedido no existente retorna null
    Cuando intento recuperar un pedido con identificador "ORD-INEXISTENTE-999"
    Entonces debo recibir una respuesta nula o vacío
    Y no debe lanzar una excepción no controlada

  @escritura @integration
  Escenario: Validar escritura de pedido con todos los campos obligatorios
    Cuando escribo un pedido con solo campos obligatorios:
      | campo          | valor                           |
      | orderId        | ORD-2024-004                   |
      | customerId     | CUST-004                       |
      | productId      | PROD-004                       |
      | quantity       | 1                              |
      | totalAmount    | 25.50                          |
      | status         | CREATED                        |
    Entonces el pedido debe ser almacenado exitosamente
    Y debo poder recuperar el pedido "ORD-2024-004"
    Y todos los campos deben coincidir con los valores originales

  @integration
  Escenario: Validar orden de los datos en la tabla DynamoDB
    Cuando escribo múltiples pedidos consecutivos:
      | orderId        | customerId   | productId   | quantity | totalAmount | status    |
      | ORD-2024-005   | CUST-005     | PROD-005    | 2        | 50.00       | PENDING   |
      | ORD-2024-006   | CUST-006     | PROD-006    | 4        | 100.00      | PENDING   |
      | ORD-2024-007   | CUST-007     | PROD-007    | 6        | 150.00      | PENDING   |
    Entonces todos los pedidos deben ser almacenados correctamente
    Y debo poder recuperar cada pedido por su identificador
    Y la cantidad total de pedidos en la tabla debe ser 3