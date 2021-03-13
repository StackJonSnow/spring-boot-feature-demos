package com.example.dynamic.datasource.aop.annotation;

import com.example.dynamic.datasource.config.DataSourceHolder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author JonSnow
 * @description 数据源选择注解
 * @date 2021/2/22
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MysqlDB {

    //数据源选择参数，默认选择主库
    String prefix() default DataSourceHolder.MASTER;
}
