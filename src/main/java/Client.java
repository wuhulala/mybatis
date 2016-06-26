import com.atguigu.mybatis_test.bean.Classes;
import com.atguigu.mybatis_test.bean.ConditionUser;
import com.atguigu.mybatis_test.bean.Order;
import com.atguigu.mybatis_test.bean.User;
import com.atguigu.mybatis_test.test3.UserMapper;
import com.atguigu.mybatis_test.test4.OrderMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     *  一对一关联1
     */
    @Test
    public void testOneToOne1(){

        SqlSession session = sessionFactory.openSession();
        String statement = "com.atguigu.mybatis_test.test6.ClassMapper"+".getClass";
        Classes order = session.selectOne(statement, 1);
        System.out.println(order);
        session.commit();

    }
    /**
     *  一对一关联2
     */
    @Test
    public void testOO2() {
        SqlSession sqlSession = sessionFactory.openSession();
        Classes c = sqlSession.selectOne("com.atguigu.mybatis_test.test6.ClassMapper.getClass2", 1);
        System.out.println(c);
    }

    /**
     *  一对多关联1
     */
    @Test
    public void testOM1() {
        SqlSession sqlSession = sessionFactory.openSession();
        Classes c = sqlSession.selectOne("com.atguigu.mybatis_test.test6.ClassMapper.getClass3", 1);
        System.out.println(c);
    }

    /**
     *  一对多关联2
     */
    @Test
    public void testOM2() {
        SqlSession sqlSession = sessionFactory.openSession();
        Classes c = sqlSession.selectOne("com.atguigu.mybatis_test.test6.ClassMapper.getClass4", 1);
        System.out.println(c);
    }

    /**
     *  动态SQL
     */
    @Test
    public void testDynamicSQL() {
        SqlSession sqlSession = sessionFactory.openSession();
        String statement = "com.atguigu.mybatis_test.test7.UserMapper.getUser";

        List<User> list = sqlSession.selectList(statement, new ConditionUser("%a%", 1, 20));
        System.out.println(list);
        sqlSession.close();
    }

    /**
     *  存储过程
     */
    @Test
    public void testStatement() {
        SqlSession sqlSession = sessionFactory.openSession();
        String statement = "com.atguigu.mybatis_test.test7.UserMapper.getCount";
        Map<String, Integer> paramMap = new HashMap<String, Integer>();
        paramMap.put("sex_id", 0);
        Object returnValue = sqlSession.selectOne(statement, paramMap);
        System.out.println("result="+paramMap.get("result"));
        System.out.println("sex_id="+paramMap.get("sex_id"));
        System.out.println("returnValue="+returnValue);

        sqlSession.close();
    }

    /**
     * 测试二级缓存
     */
    @Test
    public void testCache2(){
        String statement = "com.atguigu.mybatis_test.test8.UserMapper"+".getUser";

        SqlSession session = sessionFactory.openSession();
        User user  = session.selectOne(statement, 1);
        System.out.println(user);
        session.commit();

        SqlSession session2 = sessionFactory.openSession();
        User user2  = session.selectOne(statement, 1);
        System.out.println(user2);
        session.commit();

    }
    @After
  public void after(){

}
}
