import com.atguigu.mybatis_test.bean.Order;
import com.atguigu.mybatis_test.bean.User;
import com.atguigu.mybatis_test.test3.UserMapper;
import com.atguigu.mybatis_test.test4.OrderMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author xueaohui
 */
public class Client {

    SqlSessionFactory sessionFactory = null;
    @Before
    public void before() throws IOException {
        String resource = "conf.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    @Test
    public void testAdd(){
        SqlSession session = sessionFactory.openSession();
        String statement = "com.atguigu.mybatis_test.test2.userMapper"+".addUser";
        int rows = session.insert(statement, new User("ttt", 23));
        System.out.println(rows);
        session.commit();
    }

    @Test
    public void testUpdate(){
        SqlSession session = sessionFactory.openSession();
        String statement = "com.atguigu.mybatis_test.test2.userMapper"+".updateUser";
        int rows = session.insert(statement, new User(4,"t111tt",23));
        System.out.println(rows);
        session.commit();
    }

    @Test
    public void getAll(){
        SqlSession session = sessionFactory.openSession();
        String statement = "com.atguigu.mybatis_test.test2.userMapper"+".getAllUsers";
        List<User> rows = session.selectList(statement);
        System.out.println(rows);
        session.commit();
    }


    @Test
    public void add2(){
        SqlSession session = sessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);

        int rows = userMapper.addUser(new User("t111tt", 23));
        System.out.println(rows);

        session.commit();

    }


    /**
     *  解决字段名与实体类属性名不相同的冲突 :通过在 sql  语句中定义别名
     */
    @Test
    public void testSelectOrder1(){

        SqlSession session = sessionFactory.openSession();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        Order order = orderMapper.selectOrder(1);
        System.out.println(order);
        session.commit();

    }

    /**
     *  解决字段名与实体类属性名不相同的冲突 :通过<resultMap>
     */
    @Test
    public void testSelectOrder2(){

        SqlSession session = sessionFactory.openSession();
        String statement = "com.atguigu.mybatis_test.test5.OrderMapper"+".selectOrderResultMap";
        Order order = session.selectOne(statement, 1);
        System.out.println(order);
        session.commit();

    }

}
