package com.example.mybatis;

import com.example.mybatis.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisDemoApplicationTests {

	@Autowired
	private PersonMapper personMapper;

	private final Logger LOGGER = LoggerFactory.getLogger(MybatisDemoApplicationTests.class);

	@Test
	void findAll() {
		LOGGER.info("从DB查询：{}", personMapper.findAll().toString());
	}

	@Test
	void findById() {
		LOGGER.info("从DB查询：{}", personMapper.findById(1l));
	}

}
