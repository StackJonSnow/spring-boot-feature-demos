package com.example.redis.demo.service;

import com.example.redis.demo.model.CacheEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisCacheService {

    private final Logger LOGGER = LoggerFactory.getLogger(RedisCacheService.class);

    private final CacheEntity cacheEntity = new CacheEntity("9527", 112, new Date());

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

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

    public void writeData(String key, String value) {
        redisTemplate.opsForValue().set(key, value, 300, TimeUnit.SECONDS);
    }

    public void pipelineWrite(Map<String, String> data) {
        List<Object> result = redisTemplate.executePipelined(
            new RedisCallback<Object>() {

                @Override
                public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    redisConnection.openPipeline();

                    data.keySet().forEach(key -> {
                        try {
                            redisConnection.set(key.getBytes("UTF-8"), data.get(key).getBytes("UTF-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    });
                    return null;

                }
            }

        );

        LOGGER.info("result:{}", result);
    }

    public void pipelineRead(Set<String> keys) {
        List<Object> result = redisTemplate.executePipelined(
                new RedisCallback<Object>() {

                    @Override
                    public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                        redisConnection.openPipeline();

                        keys.forEach(key -> {
                            try {
                                redisConnection.get(key.getBytes("UTF-8"));
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        });
                        return null;

                    }
                }

        );
        LOGGER.info("values{}", result);
    }
}
