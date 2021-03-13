package com.example.druid.datasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JonSnow
 * @description 数据源配置
 * @date 2021/2/22
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource slaveDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * @Primary 上下文容器存在多个同类型bean时，优先注入声明此注解的bean
     */
    @Bean
    @Primary
    public DataSource dynamicDataSource(@Autowired DataSource masterDataSource,
                                        @Autowired DataSource slaveDataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceHolder.MASTER, masterDataSource);
        targetDataSource.put(DataSourceHolder.SLAVE, slaveDataSource);
        dynamicDataSource.setTargetDataSources(targetDataSource);
        //默认数据源
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        return dynamicDataSource;
    }
}
