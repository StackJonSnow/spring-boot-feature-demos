package com.example.rabbitmq.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {

    @Bean
    @ConditionalOnMissingBean(name = "directExchange")
    public Exchange directExchange(){
        return ExchangeBuilder.directExchange("direct-exchange").durable(true).build();
    }

    @Bean
    @ConditionalOnMissingBean(name = "directQueue")
    public Queue directQueue() {
        return QueueBuilder.durable("direct-Queue").deadLetterExchange("dead-letter-exchange")
                .deadLetterRoutingKey("direct-dead-letter").build();
    }

    @Bean
    @ConditionalOnMissingBean(name = "directBinding")
    public Binding directBinding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("direct").noargs();
    }

    //==================================================================================================================

    @Bean
    @ConditionalOnMissingBean(name = "topicExchange")
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange("topic-exchange").durable(true).build();
    }

    @Bean
    @ConditionalOnMissingBean(name = "topicQueueNumberOne")
    public Queue topicQueueNumberOne() {
        return QueueBuilder.durable("topic-Queue-one").build();
    }

    @Bean
    @ConditionalOnMissingBean(name = "topicBindingOne")
    public Binding topicBindingOne() {
        return BindingBuilder.bind(topicQueueNumberOne()).to(topicExchange()).with("topicone.#").noargs();
    }

    @Bean
    @ConditionalOnMissingBean(name = "topicQueueNumberTwo")
    public Queue topicQueueNumberTwo() {
        return QueueBuilder.durable("topic-Queue-two").build();
    }

    @Bean
    @ConditionalOnMissingBean(name = "topicBindingTwo")
    public Binding topicBindingTwo() {
        return BindingBuilder.bind(topicQueueNumberTwo()).to(topicExchange()).with("topictwo.#").noargs();
    }

    //==================================================================================================================

    @Bean
    @ConditionalOnMissingBean(name = "fanoutExchange")
    public Exchange fanoutExchange(){
        return ExchangeBuilder.fanoutExchange("fanout-exchange").durable(true).build();
    }

    @Bean
    @ConditionalOnMissingBean(name = "fanoutQueueNumberOne")
    public Queue fanoutQueueNumberOne() {
        return QueueBuilder.durable("fanout-Queue-one").build();
    }

    @Bean
    @ConditionalOnMissingBean(name = "fanoutBindingOne")
    public Binding fanoutBindingOne() {
        return BindingBuilder.bind(fanoutQueueNumberOne()).to(fanoutExchange()).with("").noargs();
    }

    @Bean
    @ConditionalOnMissingBean(name = "fanoutQueueNumberTwo")
    public Queue fanoutQueueNumberTwo() {
        return QueueBuilder.durable("fanout-Queue-two").build();
    }

    @Bean
    @ConditionalOnMissingBean(name = "fanoutBindingTwo")
    public Binding fanoutBindingTwo() {
        return BindingBuilder.bind(fanoutQueueNumberTwo()).to(fanoutExchange()).with("").noargs();
    }

    //==================================================================================================================

    @Bean
    @ConditionalOnMissingBean(name = "deadLetterExchange")
    public Exchange deadLetterExchange(){
        return ExchangeBuilder.directExchange("dead-letter-exchange").durable(true).build();
    }

    @Bean
    @ConditionalOnMissingBean(name = "deadLetterQueue")
    public Queue deadLetterQueue() {
        return QueueBuilder.durable("dead-letter-Queue").build();
    }

    @Bean
    @ConditionalOnMissingBean(name = "deadLetterBinding")
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with("direct-dead-letter").noargs();
    }

    //==================================================================================================================

    @Bean
    @ConditionalOnMissingBean(name = "headersExchange")
    public HeadersExchange headersExchange(){
        return ExchangeBuilder.headersExchange("headers-exchange").durable(true).build();
    }

    @Bean
    @ConditionalOnMissingBean(name = "headersQueueNumberOne")
    public Queue headersQueueNumberOne() {
        return QueueBuilder.durable("headers-Queue-one").build();
    }

    @Bean
    @ConditionalOnMissingBean(name = "headersQueueOneBinding")
    public Binding headersQueueOneBinding() {
        Map<String, Object> args = new HashMap<>();
        args.put("condition-one", "yes");
        args.put("condition-two", "yes");
        return BindingBuilder.bind(headersQueueNumberOne()).to(headersExchange()).whereAll(args).match();
    }

    @Bean
    @ConditionalOnMissingBean(name = "headersQueueNumberTwo")
    public Queue headersQueueNumberTwo() {
        return QueueBuilder.durable("headers-Queue-two").build();
    }

    @Bean
    @ConditionalOnMissingBean(name = "headersQueueTwoBinding")
    public Binding headersQueueTwoBinding() {
        Map<String, Object> args = new HashMap<>();
        args.put("condition-one", "yes");
        args.put("condition-two", "yes");
        return BindingBuilder.bind(headersQueueNumberTwo()).to(headersExchange()).whereAny(args).match();
    }


}
