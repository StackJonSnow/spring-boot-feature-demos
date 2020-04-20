package com.example.mybatis.plus;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatis.plus.mapper.PersonMapper;
import com.example.mybatis.plus.po.Person;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MybatisPlusDemoApplicationTests {

	@Autowired
	private PersonMapper personMapper;

	private final Logger LOGGER = LoggerFactory.getLogger(MybatisPlusDemoApplicationTests.class);

	@Test
	void findAll() {
		LOGGER.info("从DB查询：{}", personMapper.selectList(null));
	}

	@Test
	void findPage() {
		IPage<Person> page = new Page<Person>().setCurrent(2).setSize(2);
		LOGGER.info("从DB查询：{}", personMapper.selectPage(page, null).getRecords());
	}

}
