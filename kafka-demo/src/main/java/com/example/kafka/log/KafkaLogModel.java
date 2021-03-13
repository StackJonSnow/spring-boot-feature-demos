package com.example.kafka.log;


import com.alibaba.fastjson.JSON;

/**
 * @author JonSnow
 * @description TODO
 * @date 2020/5/29
 */
public class KafkaLogModel {

    private String  threadName;
    private long    timeStamp;
    private String  loggerName;
    private String  levelStr;
    private String  message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLevelStr() {
        return levelStr;
    }

    public void setLevelStr(String levelStr) {
        this.levelStr = levelStr;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String toJSONString() {
        return JSON.toJSONString(this);
    }
}
