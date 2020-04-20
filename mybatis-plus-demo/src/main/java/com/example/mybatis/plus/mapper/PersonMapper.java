package com.example.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatis.plus.po.Person;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper extends BaseMapper<Person> {


}
