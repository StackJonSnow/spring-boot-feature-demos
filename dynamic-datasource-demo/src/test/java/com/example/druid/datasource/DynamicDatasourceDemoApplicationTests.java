package com.example.druid.datasource;

import com.example.dynamic.datasource.service.MysqlOperateService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DynamicDatasourceDemoApplicationTests {

	@Autowired
	private MysqlOperateService mysqlOperateService;

	private final Logger LOGGER = LoggerFactory.getLogger(DynamicDatasourceDemoApplicationTests.class);

	@Test
	void queryDataFromDbByJdbc() {
		LOGGER.info("查询到数据：" + mysqlOperateService.queryFromDbByJdbc().toString());
	}

	@Test
	void createDataFromDbByJdbc() {
		LOGGER.info("查询到数据：" + mysqlOperateService.createFromDbByJdbc().toString());
	}

	@Test
	void queryFromMaster() {
		LOGGER.info("查询到数据：" + mysqlOperateService.fromMaster().toString());
	}

	@Test
	void queryFromSlave() {
		LOGGER.info("查询到数据：" + mysqlOperateService.fromSlave().toString());
	}
}
