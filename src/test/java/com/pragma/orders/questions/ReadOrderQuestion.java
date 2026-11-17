package com.pragma.orders.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Actor;
import com.pragma.orders.model.Order;

/**
 * Question de Screenplay para leer un pedido desde DynamoDB.
 * El candidato debe implementar la lógica de lectura.
 */
public class ReadOrderQuestion implements Question<Order> {

    private final String orderId;

    public ReadOrderQuestion(String orderId) {
        this.orderId = orderId;
    }

    public static ReadOrderQuestion withId(String orderId) {
        return new ReadOrderQuestion(orderId);
    }

    @Override
    public Order answeredBy(Actor actor) {
        // TODO(reto): implementar la lectura del pedido desde DynamoDB
        // 1. Obtener el cliente DynamoDB del proveedor
        // 2. Construir la clave de búsqueda (partition key)
        // 3. Ejecutar la operación GetItem
        // 4. Convertir los atributos devueltos a un objeto Order
        // 5. Manejar el caso cuando el pedido no existe
        throw new UnsupportedOperationException("ReadOrderQuestion no implementada");
    }
}