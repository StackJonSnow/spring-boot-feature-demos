package com.example.dynamic.datasource.service;

import com.example.dynamic.datasource.config.DataSourceHolder;
import com.example.dynamic.datasource.dao.PersonRepositoryJdbc;
import com.example.dynamic.datasource.po.PersonJdbc;
import com.example.dynamic.datasource.aop.annotation.MysqlDB;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MysqlOperateService {

    @Autowired
    private PersonRepositoryJdbc personRepositoryJdbc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<PersonJdbc> queryFromDbByJdbc() {
        return (List<PersonJdbc>) personRepositoryJdbc.findAll();
    }

    public List<PersonJdbc> createFromDbByJdbc() {
        return (List<PersonJdbc>) personRepositoryJdbc.findAll();
    }

    @MysqlDB(prefix = DataSourceHolder.MASTER)
    public List<PersonJdbc> fromMaster() {
        return (List<PersonJdbc>) personRepositoryJdbc.findAll();
    }

    @MysqlDB(prefix = DataSourceHolder.SLAVE)
    public List<PersonJdbc> fromSlave() {
        return (List<PersonJdbc>) personRepositoryJdbc.findAll();
    }
}
