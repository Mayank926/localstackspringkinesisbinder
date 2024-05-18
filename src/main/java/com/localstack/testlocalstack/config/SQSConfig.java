package com.localstack.testlocalstack.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class SQSConfig {
  @Bean
  public SqsClient sqsClient() {
    return SqsClient.builder().build();
  }
}
