package com.pragma.orders.config;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import java.net.URI;

public class DynamoDBClientProvider {

    private static final String DYNAMODB_ENDPOINT = "dynamodb.endpoint";
    private static final String AWS_REGION = "aws.region";
    private static final String AWS_ACCESS_KEY = "aws.accessKeyId";
    private static final String AWS_SECRET_KEY = "aws.secretKey";

    public DynamoDbClient createClient() {
        String endpoint = System.getProperty(DYNAMODB_ENDPOINT, "http://localhost:8000");
        String region = System.getProperty(AWS_REGION, "us-east-1");
        String accessKey = System.getProperty(AWS_ACCESS_KEY, "fakeAccessKey");
        String secretKey = System.getProperty(AWS_SECRET_KEY, "fakeSecretKey");

        return DynamoDbClient.builder()
                .endpointOverride(URI.create(endpoint))
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }
}