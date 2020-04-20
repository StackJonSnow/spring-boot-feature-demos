package com.example.mysql.dao;

import com.example.mysql.po.PersonJdbc;
import com.example.mysql.po.PersonJpa;
import org.springframework.data.repository.CrudRepository;

/**
 * 使用 spring data jpa 定义的DAO
 */
public interface PersonRepositoryJpa extends CrudRepository<PersonJpa, Long> {
}
