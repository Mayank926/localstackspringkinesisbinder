package com.localstack.testlocalstack.producer;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.model.ProvisionedThroughputExceededException;
import software.amazon.awssdk.services.kinesis.model.PutRecordRequest;
import software.amazon.awssdk.services.kinesis.model.PutRecordResponse;

@EnableScheduling
@Component
@Slf4j
public class KinesisProducer {
  @Value("${outputStream}")
  private String outputStream;
  private KinesisClient kinesisClient;

  @Autowired
  public KinesisProducer(KinesisClient kinesisClient){
    this.kinesisClient = kinesisClient;
    System.out.println("From producer"+kinesisClient.serviceClientConfiguration().endpointOverride());
  }

  /*public void send(List<ScbParsedEntity> parsedEntityList) {
    if (Objects.nonNull(parsedEntityList)) {
      parsedEntityList.stream().forEach((ScbParsedEntity e) ->
        sendMessage(SCCUtil.objectToJsonString(e), e.getChargerId())
      );
    }
  }*/

  public void send(List<String> parsedEntityList) {
    if (Objects.nonNull(parsedEntityList)) {
      parsedEntityList.stream().forEach(e ->
        sendMessage(e, e)
      );
    }
  }

  public void sendMessage(String message, String partitionKey) {
    try {
      log.info("In KinesisProducer-sendMessage, Before sending data to the stream: {}", message);
      PutRecordRequest putRecordRequest = PutRecordRequest.builder()
        .streamName(outputStream)
        .partitionKey(partitionKey)
        .data(SdkBytes.fromUtf8String(message))
        .build();
      PutRecordResponse putRecordResponse = kinesisClient.putRecord(putRecordRequest);
      String shardId = putRecordResponse.shardId();
      String sequenceNumber = putRecordResponse.sequenceNumber();
      log.info("In KinesisProducer-sendMessage, Message sent to shard {} with sequence number {} - {}",
        shardId, sequenceNumber, message);
    } catch (ProvisionedThroughputExceededException e) {
      log.error("ProvisionedThroughputExceededException occurred: {}", e.getMessage());
    } catch (AwsServiceException e) {
      log.error("AwsServiceException occurred: {}", e.getMessage());
    }
  }
}
