package com.example.mysql.dao;

import com.example.mysql.po.PersonJdbc;
import org.springframework.data.repository.CrudRepository;

/**
 * 使用 spring data jdbc 定义的DAO
 */
public interface PersonRepositoryJdbc extends CrudRepository<PersonJdbc, Long> {
}
