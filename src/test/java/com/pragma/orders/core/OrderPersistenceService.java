package com.pragma.orders.core;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import com.pragma.orders.config.DynamoDBClientProvider;
import com.pragma.orders.model.Order;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Servicio de persistencia de pedidos en DynamoDB.
 * Proporciona operaciones CRUD sobre la tabla de pedidos.
 */
public class OrderPersistenceService {

    private static final String TABLE_NAME = "orders";
    private static final String ORDER_ID_ATTRIBUTE = "orderId";
    private static final String CUSTOMER_ID_ATTRIBUTE = "customerId";
    private static final String STATUS_ATTRIBUTE = "status";
    private static final String TOTAL_AMOUNT_ATTRIBUTE = "totalAmount";
    private static final String ITEMS_ATTRIBUTE = "items";
    private static final String CREATED_AT_ATTRIBUTE = "createdAt";
    private static final String UPDATED_AT_ATTRIBUTE = "updatedAt";

    private final DynamoDbClient dynamoDBClient;

    public OrderPersistenceService() {
        DynamoDBClientProvider provider = new DynamoDBClientProvider();
        this.dynamoDBClient = provider.createClient();
    }

    /**
     * Guarda un pedido en DynamoDB.
     * @param order el pedido a persistir
     * @return el pedido guardado con timestamps actualizados
     */
    public Order saveOrder(Order order) {
        Instant now = Instant.now();
        
        Map<String, Object> attributes = order.toDynamoDbAttributes();
        Map<String, AttributeValue> item = convertToAttributeValue(attributes);
        
        item.put(ORDER_ID_ATTRIBUTE, AttributeValue.builder().s(order.orderId()).build());
        item.put(CUSTOMER_ID_ATTRIBUTE, AttributeValue.builder().s(order.customerId()).build());
        item.put(STATUS_ATTRIBUTE, AttributeValue.builder().s(order.status()).build());
        item.put(TOTAL_AMOUNT_ATTRIBUTE, AttributeValue.builder().n(String.valueOf(order.totalAmount())).build());
        item.put(ITEMS_ATTRIBUTE, AttributeValue.builder().s(order.items().toString()).build());
        item.put(CREATED_AT_ATTRIBUTE, AttributeValue.builder().s(now.toString()).build());
        item.put(UPDATED_AT_ATTRIBUTE, AttributeValue.builder().s(now.toString()).build());

        PutItemRequest putRequest = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build();

        try {
            dynamoDBClient.putItem(putRequest);
            return Order.fromDynamoDbAttributes(convertToObjectMap(item));
        } catch (DynamoDbException e) {
            throw new RuntimeException("Error al guardar el pedido en DynamoDB: " + e.getMessage(), e);
        }
    }

    /**
     * Recupera un pedido por su ID.
     * @param orderId identificador del pedido
     * @return Optional con el pedido si existe, vacío si no
     */
    public Optional<Order> findById(String orderId) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(ORDER_ID_ATTRIBUTE, AttributeValue.builder().s(orderId).build());

        GetItemRequest getRequest = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .build();

        try {
            GetItemResponse response = dynamoDBClient.getItem(getRequest);
            
            if (!response.hasItem() || response.item().isEmpty()) {
                return Optional.empty();
            }
            
            Map<String, AttributeValue> item = response.item();
            Map<String, Object> attributes = convertToObjectMap(item);
            Order order = Order.fromDynamoDbAttributes(attributes);
            return Optional.of(order);
        } catch (DynamoDbException e) {
            throw new RuntimeException("Error al leer el pedido de DynamoDB: " + e.getMessage(), e);
        }
    }

    /**
     * Actualiza el estado de un pedido.
     * @param orderId identificador del pedido
     * @param newStatus nuevo estado
     * @return el pedido actualizado
     */
    public Order updateOrderStatus(String orderId, String newStatus) {
        Instant now = Instant.now();
        
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(ORDER_ID_ATTRIBUTE, AttributeValue.builder().s(orderId).build());

        Map<String, AttributeValueUpdate> attributeUpdates = new HashMap<>();
        attributeUpdates.put(STATUS_ATTRIBUTE, AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(newStatus).build())
                .action(AttributeAction.PUT)
                .build());
        attributeUpdates.put(UPDATED_AT_ATTRIBUTE, AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(now.toString()).build())
                .action(AttributeAction.PUT)
                .build());

        UpdateItemRequest updateRequest = UpdateItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .attributeUpdates(attributeUpdates)
                .build();

        try {
            dynamoDBClient.updateItem(updateRequest);
            return findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado después de actualizar"));
        } catch (DynamoDbException e) {
            throw new RuntimeException("Error al actualizar el pedido en DynamoDB: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina un pedido por su ID.
     * @param orderId identificador del pedido
     */
    public void deleteOrder(String orderId) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(ORDER_ID_ATTRIBUTE, AttributeValue.builder().s(orderId).build());

        DeleteItemRequest deleteRequest = DeleteItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .build();

        try {
            dynamoDBClient.deleteItem(deleteRequest);
        } catch (DynamoDbException e) {
            throw new RuntimeException("Error al eliminar el pedido de DynamoDB: " + e.getMessage(), e);
        }
    }

    /**
     * Convierte un mapa de objetos a un mapa de AttributeValue de DynamoDB.
     * @param attributes mapa de atributos como objetos
     * @return mapa de AttributeValue
     */
    private Map<String, AttributeValue> convertToAttributeValue(Map<String, Object> attributes) {
        Map<String, AttributeValue> result = new HashMap<>();
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) {
                result.put(entry.getKey(), AttributeValue.builder().s((String) value).build());
            } else if (value instanceof Number) {
                result.put(entry.getKey(), AttributeValue.builder().n(value.toString()).build());
            } else if (value instanceof Boolean) {
                result.put(entry.getKey(), AttributeValue.builder().bool((Boolean) value).build());
            }
        }
        return result;
    }

    /**
     * Convierte un mapa de AttributeValue a un mapa de objetos simples.
     * @param item mapa de AttributeValue
     * @return mapa de objetos
     */
    private Map<String, Object> convertToObjectMap(Map<String, AttributeValue> item) {
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, AttributeValue> entry : item.entrySet()) {
            AttributeValue av = entry.getValue();
            if (av.s() != null) {
                result.put(entry.getKey(), av.s());
            } else if (av.n() != null) {
                result.put(entry.getKey(), av.n());
            } else if (av.bool() != null) {
                result.put(entry.getKey(), av.bool());
            } else if (av.hasL()) {
                result.put(entry.getKey(), av.l());
            } else if (av.hasM()) {
                result.put(entry.getKey(), convertToObjectMap(av.m()));
            }
        }
        return result;
    }
}