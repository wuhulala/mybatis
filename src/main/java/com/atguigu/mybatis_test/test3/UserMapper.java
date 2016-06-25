package com.atguigu.mybatis_test.test3;

import com.atguigu.mybatis_test.bean.User;
import org.apache.ibatis.annotations.Insert;

/**
 * @author xueaohui
 */
public interface UserMapper {
    @Insert("INSERT INTO users(name,age) VALUES (#{name},#{age});")
    int addUser(User user);
}
