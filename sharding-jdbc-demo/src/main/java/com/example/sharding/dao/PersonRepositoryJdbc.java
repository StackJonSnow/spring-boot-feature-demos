package com.example.sharding.dao;

import com.example.sharding.po.PersonJdbc;
import org.springframework.data.repository.CrudRepository;

/**
 * 使用 spring data jdbc 定义的DAO
 */
public interface PersonRepositoryJdbc extends CrudRepository<PersonJdbc, Long> {
}
