package com.wuhulala.mybatis;

import com.wuhulala.mybatis.entities.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wuhulala<br>
 * @date 2019/11/18<br>
 * @since v1.0<br>
 */
public class NativaMybatis {

    public static void main(String[] args) throws IOException {
        String resource = "native/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
//        TransactionFactory transactionFactory = new JdbcTransactionFactory();
//        Environment environment = new Environment("development", transactionFactory, dataSource);
//        Configuration configuration = new Configuration(environment);
//        configuration.addMapper(UserMapper.class);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            User user = session.selectOne("com.wuhulala.mybatis.mapper.UserMapper.selectByExample", 1);
            System.out.println(user);
        }
    }

}
