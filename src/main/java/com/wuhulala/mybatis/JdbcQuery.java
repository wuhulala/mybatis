package com.wuhulala.mybatis;

import java.sql.*;

/**
 * @author wuhulala<br>
 * @date 2019/11/17<br>
 * @since v1.0<br>
 */
public class JdbcQuery {


    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.1.101:3306/test?useServerPrepStmts=true&cachePrepStmts=true";
//        String url = "jdbc:mysql://192.168.1.101:3306/test";
        String user = "root";
        String password = "123456";
        try {
            // 1. 加载驱动
            Class.forName(driver);
            // 2. 建立连接
            Connection con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {//判断数据库是否链接成功
                //3、创建Statement对象
                String sql = "select * from tb_user where id = ?";//查询user表的所有信息
                String sql1 = "select * from tb_user where username = ?";


                PreparedStatement st = con.prepareStatement(sql);
                //4、执行sql语句
//              未开启 5406ms
                // 开启预编译和缓存 https://www.iteye.com/blog/cs-css-1847772
                long start = System.currentTimeMillis();
                for (int i = 1; i <= 10000; i++) {
                    st.setInt(1, i);
                    ResultSet rs = st.executeQuery();//查询之后返回结果集
                    //5、打印出结果
//                    System.out.println("ID" + "\t" + "NAME" + "\t");
                    while (rs.next()) {
//                        System.out.println(rs.getString("Id") + "\t" + rs.getString("name") + "\t");
                    }

                    rs.close();//关闭资源
                }
                System.out.println("总共花费了" + (System.currentTimeMillis() - start) + "ms");
                con.close();//关闭数据库

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}