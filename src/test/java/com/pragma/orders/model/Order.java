package com.pragma.orders.model;

import java.util.Map;
import java.util.HashMap;

public record Order(
        String orderId,
        String customerId,
        String status,
        double totalAmount,
        Map<String, Integer> items) {

    public Map<String, Object> toDynamoDbAttributes() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("orderId", orderId);
        attributes.put("customerId", customerId);
        attributes.put("status", status);
        attributes.put("totalAmount", totalAmount);
        attributes.put("items", items);
        return attributes;
    }

    public static Order fromDynamoDbAttributes(Map<String, Object> attributes) {
        return new Order(
                (String) attributes.get("orderId"),
                (String) attributes.get("customerId"),
                (String) attributes.get("status"),
                (double) attributes.get("totalAmount"),
                (Map<String, Integer>) attributes.get("items")
        );
    }
}