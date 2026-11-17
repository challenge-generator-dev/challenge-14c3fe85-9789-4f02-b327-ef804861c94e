package com.pragma.orders.config;

import org.junit.jupiter.api.BeforeEach;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import java.util.List;

public class BaseTestSetup {

    protected DynamoDbClient dynamoDbClient;
    protected static final String TABLE_NAME = "Orders";

    @BeforeEach
    void setUp() {
        // TODO(reto): Implementar la inicialización de la tabla DynamoDB para pruebas
        // 1. Crear el cliente DynamoDB usando DynamoDBClientProvider
        // 2. Crear la tabla "Orders" con la clave primaria "orderId" (String)
        // 3. Asegurar que la tabla esté disponible antes de ejecutar las pruebas
    }
}