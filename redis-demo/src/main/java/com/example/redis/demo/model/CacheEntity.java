package com.example.redis.demo.model;

import java.util.Date;

public class CacheEntity {

    private String id;

    private int ttl;

    private Date saveTime;

    public CacheEntity(){}

    public CacheEntity(String id, int ttl, Date saveTime) {
        this.id = id;
        this.ttl = ttl;
        this.saveTime = saveTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    @Override
    public String toString() {
        return "CacheEntity{" +
                "id='" + id + '\'' +
                ", ttl=" + ttl +
                ", saveTime=" + saveTime +
                '}';
    }
}
