package com.example.sharding.po;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

//使用spring data jdbc, 数据库表格对应的实体类
@Table("person")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PersonJdbc {

    @Id
    private Long id;
    @Column("age")
    private Integer age;
    @Column("name")
    private String name;

}
