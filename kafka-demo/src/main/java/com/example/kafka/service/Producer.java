package com.example.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class Producer {

    private final KafkaTemplate kafkaTemplate;

    private final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Autowired
    public Producer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishStringMessage() {
        ListenableFuture listenableFuture = kafkaTemplate.send("test", "hello kafka");
        try {
            Object o = listenableFuture.get();

            LOGGER.info("消息发送结果：{}", o.toString());
        } catch (Exception e) {
            LOGGER.error("消息发送出错", e);
        }
    }
}
