package com.pragma.orders.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Actor;
import com.pragma.orders.model.Order;

/**
 * Task de Screenplay para escribir un pedido en DynamoDB.
 * El candidato debe implementar la lógica de persistencia.
 */
public class WriteOrderTask implements Task {

    private final Order order;

    public WriteOrderTask(Order order) {
        this.order = order;
    }

    public static WriteOrderTask withOrder(Order order) {
        return new WriteOrderTask(order);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // TODO(reto): implementar la escritura del pedido en DynamoDB
        // 1. Obtener el cliente DynamoDB del proveedor
        // 2. Crear el item con los atributos del pedido
        // 3. Ejecutar la operación PutItem
        // 4. Manejar errores de escritura
        throw new UnsupportedOperationException("WriteOrderTask no implementada");
    }
}