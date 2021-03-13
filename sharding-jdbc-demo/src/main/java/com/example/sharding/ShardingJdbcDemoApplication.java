package com.example.sharding;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

//这个注解用于扫描spring data jdbc 的 dao 定义
@EnableJdbcRepositories(basePackages = "com.example.sharding")
//排除数据源自动配置
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
public class ShardingJdbcDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingJdbcDemoApplication.class, args);
	}

}
