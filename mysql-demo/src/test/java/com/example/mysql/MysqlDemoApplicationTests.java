package com.example.mysql;

import com.example.mysql.service.MysqlOperateService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * spring-jdbc 提供了 JdbcTemplate 方便对数据库操作，其实是对原生 jdbc api 的封装
 * spring-data-jdbc 提供了一个操作数据库的 orm 框架，相比 jpa 更轻量级
 * spring-data-jpa 基于 hibernate 的一个 spring 提供的数据库 orm 框架
 */
@SpringBootTest
class MysqlDemoApplicationTests {

	@Autowired
	private MysqlOperateService mysqlOperateService;

	private final Logger LOGGER = LoggerFactory.getLogger(MysqlDemoApplicationTests.class);

	@Test
	void queryDataFromDbByJdbcTemplate() {
		LOGGER.info("查询到数据：" + mysqlOperateService.queryFromDbByJdbcTemplate().toString());
	}

	@Test
	void queryDataFromDbByJpa() {
		LOGGER.info("查询到数据：" + mysqlOperateService.queryFromDbByJpa().toString());
	}

	@Test
	void queryDataFromDbByJdbc() {
		LOGGER.info("查询到数据：" + mysqlOperateService.queryFromDbByJdbc().toString());
	}

}
