package com.localstack.testlocalstack.config;


import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.kinesis.KinesisClient;

@TestConfiguration
public class TestConfig {
  /*@Bean
  public DynamoDbClient dynamoDbClient() throws URISyntaxException {

    DynamoDbClient dbClient = DynamoDbClient.builder()
      .endpointOverride(new URI("http://localhost:4566"))
      .build();
    System.out.println("Endpoint is "+dbClient.serviceClientConfiguration().endpointOverride());
    System.out.println("region is "+ dbClient.serviceClientConfiguration().region());
    return dbClient;
  }*/

  /*@Bean
  public KinesisClient kinesisClient() throws URISyntaxException {
    System.out.println("Taking kinesis config from test configurations");
    return KinesisClient.builder()
      .endpointOverride(new URI("http://localstock:4566"))
      .httpClient(UrlConnectionHttpClient.builder().build())
      .build();
  }*/
}
