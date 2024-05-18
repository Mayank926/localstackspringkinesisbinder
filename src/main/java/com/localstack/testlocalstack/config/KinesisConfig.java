package com.localstack.testlocalstack.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.services.kinesis.KinesisClient;

@Configuration
public class KinesisConfig {

  @Bean
  @Profile("!"+Constants.LOCALSTACK_PROFILE)
  public KinesisClient kinesisClient() {
    System.out.println("Kinesis client built from default profile");
    return KinesisClient.builder()
      .httpClient(UrlConnectionHttpClient.builder().build()).build();
  }

}
