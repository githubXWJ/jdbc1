package Test01;

import java.sql.*;

/**
 * @date 2022/10/27 - 16:31
 */
public class Test01 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement sta = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//注册驱动
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","150798777278x");//获取里=连接
            sta = conn.createStatement();//获取数库操作对象
            String sql = "select * from dept2";
            rs = sta.executeQuery(sql);
            while (rs.next()){
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                System.out.println("部门编号:"+deptno+"\t\t部门名称:"+dname+"\t\t部门位置:"+loc);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (sta != null) {
                try {
                    sta.close();
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
}
