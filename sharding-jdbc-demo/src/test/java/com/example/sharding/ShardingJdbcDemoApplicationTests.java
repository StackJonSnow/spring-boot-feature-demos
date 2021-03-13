package com.example.sharding;

import com.example.sharding.service.MysqlOperateService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShardingJdbcDemoApplicationTests {

	@Autowired
	private MysqlOperateService mysqlOperateService;

	private final Logger LOGGER = LoggerFactory.getLogger(ShardingJdbcDemoApplicationTests.class);

	@Test
	void query() {
		LOGGER.info("查询到数据：" + mysqlOperateService.query().toString());
	}

	@Test
	void create() {
		mysqlOperateService.create();
		LOGGER.info("数据插入成功");
	}
}
