package com.example.sharding.service;

import com.example.sharding.po.PersonJdbc;
import com.example.sharding.dao.PersonRepositoryJdbc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MysqlOperateService {

    @Autowired
    private PersonRepositoryJdbc personRepositoryJdbc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<PersonJdbc> query() {
        return (List<PersonJdbc>) personRepositoryJdbc.findAll();
    }

    public void create() {
        PersonJdbc personJdbc = new PersonJdbc();
        Random random = new Random();
        personJdbc.setAge(random.nextInt(1000));
        personJdbc.setName("master" + random.nextInt(1000));
        personRepositoryJdbc.save(personJdbc);
    }
}
