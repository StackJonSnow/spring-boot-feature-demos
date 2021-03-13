package com.example.security.cache;

import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JonSnow
 * @description 简单实现用户缓存，用户信息修改后缓存需要置无效
 * @date 2020/11/20
 */
public class UserCacheImpl implements UserCache {

    private Map<String, UserDetails> cache = new ConcurrentHashMap<>();

    @Override
    public UserDetails getUserFromCache(String s) {
        return cache.get(s);
    }

    @Override
    public void putUserInCache(UserDetails userDetails) {
        if(userDetails == null) {
            return;
        }
        cache.put(userDetails.getUsername(), userDetails);
    }

    @Override
    public void removeUserFromCache(String s) {
        cache.remove(s);
    }
}
