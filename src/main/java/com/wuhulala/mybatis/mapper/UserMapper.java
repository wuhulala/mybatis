package com.wuhulala.mybatis.mapper;

import com.wuhulala.mybatis.entities.User;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2018/2/5
 */
public interface UserMapper {
    User selectByExample(User queryItem);

    int updateOne(User newUser);
}
