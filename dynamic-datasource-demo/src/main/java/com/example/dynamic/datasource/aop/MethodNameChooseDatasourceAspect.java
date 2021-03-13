package com.example.dynamic.datasource.aop;

import com.example.dynamic.datasource.config.DataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author JonSnow
 * @description 根据方法名称选择数据源切面
 * @date 2021/2/22
 */
@Component
@Aspect
public class MethodNameChooseDatasourceAspect {

    /**
     * 根据方法名设置数据源
     * @param point
     */
    @Before("execution(public * com.example.dynamic.datasource.service.*.*(..))")
    public void chooseDataSource(JoinPoint point) {
        String methodName = point.getSignature().getName();
        if (methodName.startsWith("find")
                || methodName.startsWith("query")
                || methodName.startsWith("select")) {
            DataSourceHolder.setSlave();
            return;
        }

        if (methodName.startsWith("create")
                || methodName.startsWith("insert")
                || methodName.startsWith("add")
                || methodName.startsWith("update")
                || methodName.startsWith("delete")) {
            DataSourceHolder.setMaster();
        }
    }

}
