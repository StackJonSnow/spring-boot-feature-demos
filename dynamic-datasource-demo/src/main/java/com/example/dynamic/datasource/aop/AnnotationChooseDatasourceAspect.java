package com.example.dynamic.datasource.aop;

import com.example.dynamic.datasource.config.DataSourceHolder;
import com.example.dynamic.datasource.aop.annotation.MysqlDB;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author JonSnow
 * @description 根据注解选择主从数据源切面
 * @date 2021/2/22
 */
@Component
@Aspect
public class AnnotationChooseDatasourceAspect {

    /**
     * 根据注解设置数据源
     * @param point
     */
    @Before("execution(public * com.example.dynamic.datasource.service.*.*(..))")
    public void chooseDataSource(JoinPoint point) {
        MethodSignature methodSignature = (MethodSignature)point.getSignature();
        MysqlDB mysqlDB = methodSignature.getMethod().getAnnotation(MysqlDB.class);
        if (Objects.nonNull(mysqlDB)) {
            DataSourceHolder.setPrefix(mysqlDB.prefix());
        }
    }

}
