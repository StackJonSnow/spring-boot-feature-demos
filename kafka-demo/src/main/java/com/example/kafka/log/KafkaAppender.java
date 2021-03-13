package com.example.kafka.log;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.StringReader;
import java.util.Objects;
import java.util.Properties;

/**
 * @author JonSnow
 * @description TODO
 * @date 2020/5/29
 */
public class KafkaAppender extends AppenderBase<LoggingEvent> {

    private String topic;
    private KafkaProducer<String, String> kafkaProducer;
    private Properties properties;
    private String kafkaProducerProperties;

    @Override
    public void start() {
        super.start();

        //读取kafka配置
        properties = new Properties();
        try {
            properties.load(new StringReader(kafkaProducerProperties));
        } catch (IOException e) {
            System.out.println("Kafka配置加载失败");
            e.printStackTrace();
        }

        //初始化Kafka客户端实例
        kafkaProducer = new KafkaProducer<>(properties);
    }

    @Override
    public void stop() {
        super.stop();
        if (Objects.nonNull(kafkaProducer)) {
            kafkaProducer.flush();
            kafkaProducer.close();
        }
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        KafkaLogModel kafkaLogModel = new KafkaLogModel();
        kafkaLogModel.setMessage(loggingEvent.getMessage());
        kafkaLogModel.setLevelStr(loggingEvent.getLevel().levelStr);
        kafkaLogModel.setThreadName(loggingEvent.getThreadName());
        kafkaLogModel.setTimeStamp(loggingEvent.getTimeStamp());
        kafkaLogModel.setLoggerName(loggingEvent.getLoggerName());

        if (Objects.nonNull(kafkaProducer)) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, kafkaLogModel.toJSONString());
            try {
                kafkaProducer.send(producerRecord);
                System.out.println("日志发送Kafka成功[" + kafkaLogModel.toJSONString() + "]");
            } catch (Exception e) {
                System.out.println("日志发送Kafka失败[" + kafkaLogModel.toJSONString() + "]");
                e.printStackTrace();
            }
            return;
        }
        System.out.println("kafka客户端实例未初始化");
    }


    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setKafkaProducerProperties(String kafkaProducerProperties) {
        this.kafkaProducerProperties = kafkaProducerProperties;
    }
}
