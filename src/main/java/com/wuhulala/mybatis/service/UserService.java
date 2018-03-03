package com.wuhulala.mybatis.service;

import com.wuhulala.mybatis.entities.User;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2018/2/5
 */
public interface UserService {
    /**
     *
     * @param query
     * @return
     */
    User findOne(User query);

    int updateOne(User newUser);

    int myUpdateOne1(User newUser);

    int myUpdateOne2(User newUser);
}
