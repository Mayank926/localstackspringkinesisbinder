package com.localstack.testlocalstack.consumer;


import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.localstack.testlocalstack.producer.KinesisProducer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KinesisListener {

  private KinesisProducer kinesisProducer;

  @Autowired
  public KinesisListener(KinesisProducer kinesisProducer) {
    this.kinesisProducer = kinesisProducer;
  }

  @Bean
  public Consumer<Object> input() {
    return this::handleInput;
  }

  private void handleInput(Object inputString) {
    log.info("Received data {}",inputString.toString());
    kinesisProducer.send(List.of(inputString.toString()));
  }

}
