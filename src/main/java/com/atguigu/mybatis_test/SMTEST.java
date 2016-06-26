package com.atguigu.mybatis_test;

import com.atguigu.mybatis_test.bean.Person;
import com.atguigu.mybatis_test.dao.PersonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @author xueaohui
 */
@RunWith(SpringJUnit4ClassRunner.class) //使用Springtest框架
@ContextConfiguration("/com/atguigu/mybatis_test/test9/spring-dao.xml") //加载配置
public class SMTEST {
    @Autowired //注入
    private PersonMapper userMapper;
    @Test
    public void save() {
        Person user = new Person();
        user.setBirthday(new Date());
        user.setName("marry");
        user.setSalary(300);
        userMapper.save(user);
        System.out.println(user.getId());
    }
    @Test
    public void update() {
        Person user = userMapper.findById(2);
        user.setSalary(2000);
        userMapper.update(user);
    }
    @Test
    public void delete() {
        userMapper.delete(3);
    }
    @Test
    public void findById() {
        Person user = userMapper.findById(1);
        System.out.println(user);
    }
    @Test
    public void findAll() {
        List<Person> users = userMapper.findAll();
        System.out.println(users);
    }
}