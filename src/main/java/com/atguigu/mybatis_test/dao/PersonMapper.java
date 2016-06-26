package com.atguigu.mybatis_test.dao;

import com.atguigu.mybatis_test.bean.Person;

import java.util.List;

/**
 * @author xueaohui
 */
public interface PersonMapper {
    void save(Person user);
    void update(Person user);
    void delete(int id);
    Person findById(int id);
    List<Person> findAll();
}
