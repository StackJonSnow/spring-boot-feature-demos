package com.example.dynamic.datasource.dao;

import com.example.dynamic.datasource.po.PersonJdbc;
import org.springframework.data.repository.CrudRepository;

/**
 * 使用 spring data jdbc 定义的DAO
 */
public interface PersonRepositoryJdbc extends CrudRepository<PersonJdbc, Long> {
}
