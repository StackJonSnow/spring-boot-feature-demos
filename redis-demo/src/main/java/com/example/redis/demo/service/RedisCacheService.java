package com.example.redis.demo.service;

import com.example.redis.demo.model.CacheEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RedisCacheService {

    private final Logger LOGGER = LoggerFactory.getLogger(RedisCacheService.class);

    private final CacheEntity cacheEntity = new CacheEntity("9527", 112, new Date());

//    @Cacheable(cacheNames = "cache1", key = "#key")
//    public String getString(String key, String key1) {
//        LOGGER.info("从db获取数据。。。");
//        return "hello spring cache";
//    }

    @Cacheable(cacheNames = "cache1", keyGenerator = "paramsKeyGenerator")
    public String getString(String key, String key1) {
        LOGGER.info("从db获取数据。。。");
        return "hello spring cache";
    }

    @Cacheable(cacheNames = "cache2", keyGenerator = "paramsKeyGenerator")
    public CacheEntity getObject(String key, String key1) {
        LOGGER.info("从db获取数据。。。");
        return cacheEntity;
    }

}
