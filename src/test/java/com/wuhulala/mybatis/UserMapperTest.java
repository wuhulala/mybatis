package com.wuhulala.mybatis;

import com.wuhulala.mybatis.entities.User;
import com.wuhulala.mybatis.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2018/2/5
 */
public class UserMapperTest extends BaseTest {


    @Autowired
    UserMapper mapper;

    @Test
    public void selectByExample() throws Exception {
        User query = new User();
        query.setId(1);

        User result = mapper.selectByExample(query);
//        for (int i = 0; i < 10; i++) {
//            mapper.selectByExample(query);
//        }
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateOne() throws Exception {
        User query = new User();
        query.setId(1);

        User result = mapper.selectByExample(query);
        System.out.println(result);

        result.setName("呜呼啦啦");
        mapper.updateOne(result);

        User newResult = mapper.selectByExample(query);
        System.out.println(newResult);

        Assert.assertEquals("呜呼啦啦", newResult.getName());
    }

}