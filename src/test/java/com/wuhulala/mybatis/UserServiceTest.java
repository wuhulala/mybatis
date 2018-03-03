package com.wuhulala.mybatis;

import com.wuhulala.mybatis.entities.User;
import com.wuhulala.mybatis.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2018/2/5
 */
public class UserServiceTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
    @Autowired
    UserService service;

    @Test
    public void findOne() throws Exception {
        User query = new User();
        query.setId(1);
        User result = service.findOne(query);
        User result2 = service.findOne(query);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateOne() throws Exception {
        User query = new User();
        query.setId(1);

        User result = service.findOne(query);
        System.out.println(result);

        result.setName("呜呼啦啦2");
        result.setSex("女");
        try {
            service.myUpdateOne2(result);
        } catch (Exception e) {
            logger.error("update error ....", e);
        }

        User newResult = service.findOne(query);
        System.out.println(newResult);

        Assert.assertEquals("呜呼啦啦", newResult.getName());
    }


    @Test
    public void testPaginationRequired() {
        User query = new User();
        query.setId(1);

        User result = service.findOne(query);
        System.out.println(result);

        result.setName("呜呼啦啦23334");
        result.setSex("女");

        try {
            service.myUpdateOne2(result);
        } catch (Exception e) {
            logger.error("update error ....", e);
        }

        User newResult = service.findOne(query);
        System.out.println(newResult);

        Assert.assertEquals("呜呼啦啦23334", newResult.getName());
        Assert.assertEquals(23, newResult.getAge());
    }
}