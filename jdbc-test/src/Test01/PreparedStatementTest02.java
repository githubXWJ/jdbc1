package Test01;

import java.sql.*;

/**
 * @date 2022/10/27 - 17:18
 * 这个程序是用来测试JDBC的自动提交机制的
 * 可以关闭自动提交机制 conn.setAutoCommit
 * 手动提交 conn.commit
 * conn.rollback
 */
public class PreparedStatementTest02 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//注册驱动
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","150798777278x");
            conn.setAutoCommit(false);//关闭自动提交机制(开启事务)
            //String sql = "delete from dept2 where deptno=?";
            //获取预编译的数据库操作-对象
            String sql = "update t_account set balance = ? where actno = ?";
            ps = conn.prepareStatement(sql);
            //给?传值
            ps.setDouble(1,10000);
            ps.setInt(2,111);
            int count = ps.executeUpdate();
            //自己添加异常
            String s = null;
            s.toString();
            //在给?传值
            ps.setDouble(1,10000);
            ps.setInt(2,222);
            count =count+ps.executeUpdate();
            System.out.println(count==2?"转账成功":"转账失败");
            //手动提交事务
            conn.commit();
        } catch (Exception e) {
            //回滚事务
            if (conn!=null){
                try {
                    conn.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (rs != null) {
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
}
