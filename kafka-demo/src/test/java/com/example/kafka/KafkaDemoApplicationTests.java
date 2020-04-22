package com.example.kafka;

import com.example.kafka.service.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KafkaDemoApplicationTests {

	@Autowired
	private Producer producer;

	@Test
	void publishMessage() {
		producer.publishStringMessage();
	}

}
