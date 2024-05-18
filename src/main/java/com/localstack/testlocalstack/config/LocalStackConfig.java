package com.localstack.testlocalstack.config;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisClient;

@Profile(Constants.LOCALSTACK_PROFILE)
@Configuration
public class LocalStackConfig {
  static {
    System.out.println("Came to localstackconfig");
    //System.setProperty("spring.profiles.active",Constants.LOCALSTACK_PROFILE);
    System.setProperty("aws.region", "us-east-1");
    System.setProperty("aws.accessKeyId", "test");
    System.setProperty("aws.secretAccessKey", "test");
    System.setProperty("aws.secretKey", "test");
    System.setProperty(Constants.LOCALSTACK_URL,"http://localhost:4566");
    System.setProperty("spring.cloud.aws.dynamodb.endpoint", "http://localhost:4566");

    System.setProperty("aws.cborEnabled","false");
    System.setProperty("AWS_CBOR_DISABLE","true");
  }

  @Bean
  @Profile(Constants.LOCALSTACK_PROFILE)
  public KinesisClient kinesisClient() throws URISyntaxException, IOException {
    System.out.println("Kinesis client built from localstack profile");

    KinesisClient client = KinesisClient.builder()
      .httpClient(UrlConnectionHttpClient.builder().build())
      .endpointOverride(new URI("http://localhost:4566"))
      .region(Region.US_EAST_1)
      .build();

    System.out.println("Printing accessKey"+SystemPropertyCredentialsProvider.create()
      .resolveCredentials().accessKeyId());
    System.out.println("kinesis client details "+client.serviceClientConfiguration()
      .endpointOverride()+" "+client.serviceClientConfiguration().region());
    return client;
  }
}
