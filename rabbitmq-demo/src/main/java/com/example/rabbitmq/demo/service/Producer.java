package com.example.rabbitmq.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    private String message = "hello rabbit!";

    public void publishDirectMessage() {
        try {
            amqpTemplate.convertAndSend("direct-exchange", "direct", message);
            LOGGER.info("成功发送消息：{}", message);
        } catch (AmqpException e) {
            LOGGER.error("发送消息出错", e);
        }

    }

    public void publishTopicMessage() {
        try {
            amqpTemplate.convertAndSend("topic-exchange", "topicone.9527", message);
            amqpTemplate.convertAndSend("topic-exchange", "topictwo.9527", message);
            LOGGER.info("成功发送消息：{}", message);
        } catch (AmqpException e) {
            LOGGER.error("发送消息出错", e);
        }

    }

    public void publishFanoutMessage() {
        try {
            amqpTemplate.convertAndSend("fanout-exchange", null, message);
            LOGGER.info("成功发送消息：{}", message);
        } catch (AmqpException e) {
            LOGGER.error("发送消息出错", e);
        }

    }

    public void publishHeadersMessage() {
        try {
            Map<String, Object> args = new HashMap<>();
            args.put("condition-one", "yes");
//            args.put("condition-two", "yes");
            Message m = new Message(message.getBytes(), MessagePropertiesBuilder.newInstance()
                    .copyHeaders(args).build());
            amqpTemplate.send("headers-exchange", null, m);
            LOGGER.info("成功发送消息：{}", this.message);
        } catch (AmqpException e) {
            LOGGER.error("发送消息出错", e);
        }

    }
}
