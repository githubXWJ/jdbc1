package utils;

import java.sql.*;

/**
 * @date 2022/10/27 - 20:50
 * JDBC工具类, 简化JDBC编程
 */
public class DBUtil {

    private DBUtil(){
    }

    static {//静态代码块在类加载时执行，并且只执行一次
        try {
            Class.forName("com.mysql.jdbc.Driver");//注册驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws SQLException{
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","x");
          return conn;
    }

    /**
     * 关闭资源
     * @param conn 连接对象
     * @param ps  数据库操作对象
     * @param rs 结果集
     */
    public static void close(Connection conn, Statement ps, ResultSet rs){
        if (rs !=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
