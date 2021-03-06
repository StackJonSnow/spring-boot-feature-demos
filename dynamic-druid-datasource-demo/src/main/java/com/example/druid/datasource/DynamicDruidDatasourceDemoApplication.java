package com.example.druid.datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

//这个注解用于扫描spring data jdbc 的 dao 定义
@EnableJdbcRepositories(basePackages = "com.example.druid.datasource")
//排除数据源自动配置
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DynamicDruidDatasourceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicDruidDatasourceDemoApplication.class, args);
	}

}
