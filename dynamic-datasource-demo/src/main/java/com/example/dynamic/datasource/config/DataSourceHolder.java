package com.example.dynamic.datasource.config;

/**
 * @author JonSnow
 * @description 存储，获取数据源标识
 * @date 2021/2/22
 */
public class DataSourceHolder {

    private static final ThreadLocal<String> DATASOURCE_PREFIX = new ThreadLocal<>();

    public static final String MASTER = "master";
    public static final String SLAVE = "slave";

    public static String getPrefix() {
        return DATASOURCE_PREFIX.get();
    }

    public static void setPrefix(String prefix) {
        DATASOURCE_PREFIX.set(prefix);
    }

    public static void setMaster() {
        DATASOURCE_PREFIX.set(MASTER);
    }

    public static void setSlave() {
        DATASOURCE_PREFIX.set(SLAVE);
    }
}
