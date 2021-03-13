package com.example.druid.datasource.dao;

import com.example.druid.datasource.po.PersonJdbc;
import org.springframework.data.repository.CrudRepository;

/**
 * 使用 spring data jdbc 定义的DAO
 */
public interface PersonRepositoryJdbc extends CrudRepository<PersonJdbc, Long> {
}
