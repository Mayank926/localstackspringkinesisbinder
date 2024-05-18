package com.localstack.testlocalstack;


import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.junit4.SpringRunner;

import com.localstack.testlocalstack.config.Constants;
import com.localstack.testlocalstack.config.TestConfig;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@SpringBootTest
@Import(TestConfig.class)
class TestLocalstackApplicationTests {

  static {
    //System.setProperty("AWS_PROFILE", "localstack");
    System.setProperty("spring.profiles.active",Constants.LOCALSTACK_PROFILE);
    System.setProperty("aws.region", "us-east-1");
    System.setProperty("aws.accessKeyId", "test");
    System.setProperty("aws.secretAccessKey", "test");
    System.setProperty("aws.secretKey", "test");
    System.setProperty(Constants.LOCALSTACK_URL,"http://localhost:4566");
    /*System.setProperty("software.amazon.awssdk.http.service.impl",
      "software.amazon.awssdk.http.urlconnection.UrlConnectionSdkHttpService");*/
    /*System.setProperty("software.amazon.awssdk.http.async.service.impl",
      "software.amazon.awssdk.http.nio.netty.NettySdkAsyncHttpService");*/
    /*
    * Required to change the dynamodb endpoint internally created by spring kinesis binder
    * endpoint to localstack
    * */
    System.setProperty("spring.cloud.aws.dynamodb.endpoint", "http://localhost:4566");
    //System.setProperty("spring.main.allow-bean-definition-overriding","true");
    /*System.setProperty("spring.cloud.aws.credentials.access-key", "test");
    System.setProperty("spring.cloud.aws.credentials.secret-key","test");
    System.setProperty("spring.cloud.aws.dynamodb.region", "us-east-1");*/
  }


  @Test
  void contextLoads() {
  }

}
