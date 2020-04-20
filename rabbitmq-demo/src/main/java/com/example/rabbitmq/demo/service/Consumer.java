package com.example.rabbitmq.demo.service;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class Consumer {

    private final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

//    @RabbitListener(queues = "direct-Queue")
//    public void processDirectQueueMessage(String message) {
//        LOGGER.info("从direct-Queue收到消息：" + message);
//    }

    @RabbitListener(queues = "direct-Queue")
    @RabbitHandler
    public void processDirectQueueMessage(Channel channel, Message message) {
        LOGGER.info("从direct-Queue收到消息：{}", new String(message.getBody()));
        try {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            LOGGER.error("消息确认异常：", e);
        }
    }

    //==================================================================================================================

    @RabbitListener(queues = "topic-Queue-one")
    public void processTopicQueueOneMessage(String message) {
        LOGGER.info("从topic-Queue-one收到消息：" + message);
    }

    @RabbitListener(queues = "topic-Queue-two")
    public void processTopicQueueTwoMessage(String message) {
        LOGGER.info("从topic-Queue-two收到消息：" + message);
    }

    @RabbitListener(queues = "fanout-Queue-one")
    public void processFanoutQueueOneMessage(String message) {
        LOGGER.info("从fanout-Queue-one收到消息：" + message);
    }

    @RabbitListener(queues = "fanout-Queue-two")
    public void processFanoutQueueTwoMessage(String message) {
        LOGGER.info("从fanout-Queue-two收到消息：" + message);
    }

    //==================================================================================================================

    @RabbitListener(queues = "dead-letter-Queue")
    public void processDeadLetterQueueMessage(String message) {
        LOGGER.info("从dead-letter-Queue收到消息：" + message);
    }

    //==================================================================================================================

    @RabbitListener(queues = "headers-Queue-one")
    public void processHeadersQueueOneMessage(String message) {
        LOGGER.info("从headers-Queue-one收到消息：" + message);
    }

    @RabbitListener(queues = "headers-Queue-two")
    public void processHeadersQueueTwoMessage(String message) {
        LOGGER.info("从headers-Queue-two收到消息：" + message);
    }
}
