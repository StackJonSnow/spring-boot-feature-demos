package com.example.mysql.service;

import com.example.mysql.dao.PersonRepositoryJdbc;
import com.example.mysql.dao.PersonRepositoryJpa;
import com.example.mysql.po.PersonJdbc;
import com.example.mysql.po.PersonJpa;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MysqlOperateService {

    @Autowired
    private PersonRepositoryJdbc personRepositoryJdbc;

    @Autowired
    private PersonRepositoryJpa personRepositoryJpa;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<PersonJdbc> queryFromDbByJdbcTemplate() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from person");

        List<PersonJdbc> list = objectMapper.convertValue(maps, List.class);

        return list;
    }

    public List<PersonJdbc> queryFromDbByJdbc() {

        return (List<PersonJdbc>) personRepositoryJdbc.findAll();
    }

    public List<PersonJpa> queryFromDbByJpa() {

        return (List<PersonJpa>) personRepositoryJpa.findAll();
    }
}
