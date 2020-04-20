package com.example.rabbitmq.demo;

import com.example.rabbitmq.demo.service.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqDemoApplicationTests {

	@Autowired
	private Producer producer;

	@Test
	void sendDirectMessage() {
		producer.publishDirectMessage();
	}

	@Test
	void sendTopicMessage() {
		producer.publishTopicMessage();
	}

	@Test
	void sendFanouMessage() {
		producer.publishFanoutMessage();
	}

	@Test
	void sendHeadersMessage() {
		producer.publishHeadersMessage();
	}
}
