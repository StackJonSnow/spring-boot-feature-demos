package com.example.redis.demo;

import com.example.redis.demo.service.RedisCacheService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

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

	@Test
	void writeData() {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		for(int i = 0; i < 100000; i++) {
			String key = RandomStringUtils.random(6, chars);
			String value = RandomStringUtils.random(6, chars);
			redisCacheService.writeData(key, value);
			LOGGER.info("成功向集群写入key[{}]->value[{}]", key, value);
		}
	}

	@Test
	void pipeline() {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		int n = 100;
		HashMap<String, String> map = new HashMap<>(n);
		for(int i = 0; i < n; i++) {
			String key = RandomStringUtils.random(6, chars);
			String value = RandomStringUtils.random(6, chars);
			map.put(key, value);
		}

		new Thread(() -> redisCacheService.pipelineRead(map.keySet())).start();
		redisCacheService.pipelineWrite(map);
		LOGGER.info("redis管道写执行成功");

	}

}
