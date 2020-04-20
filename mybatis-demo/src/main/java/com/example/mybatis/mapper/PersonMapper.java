package com.example.mybatis.mapper;

import com.example.mybatis.po.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PersonMapper {

    List<Person> findAll();

    Person findById(@Param("id") Long id);

}
