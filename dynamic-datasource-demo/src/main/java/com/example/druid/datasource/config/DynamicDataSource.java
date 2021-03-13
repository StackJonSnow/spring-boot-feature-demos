package com.example.sharding.jdbc.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author JonSnow
 * @description 动态路由数据源
 * @date 2021/2/22
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getPrefix();
    }

}
