package com.example.redis.demo;

import com.example.redis.demo.service.RedisCacheService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisDemoApplicationTests {

	@Autowired
	private RedisCacheService redisCacheService;

	private final Logger LOGGER = LoggerFactory.getLogger(RedisDemoApplicationTests.class);

	@Test
	void getString() {
		LOGGER.info("获取到数据：{}", redisCacheService.getString("key", "key1"));
	}

	@Test
	void getObject() {
		LOGGER.info("获取到数据：{}", redisCacheService.getObject("cache1", null));
	}

}
