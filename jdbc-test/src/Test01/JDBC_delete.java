package Test01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @date 2022/10/23 - 20:38
 */
public class JDBC_delete {
    public static void main(String[] args) {
        //1注册驱动
        Connection conn = null;
        Statement  sta = null;
        try {
            java.sql.Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","x");
            sta = conn.createStatement();
            String sql = "delete from dept2 where deptno=80";
            int count = sta.executeUpdate(sql);
            System.out.println(count==1?"删除成功":"删除失败");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (sta != null) {
                try {
                    sta.close();
                } catch (SQLException e) {
                    e.printStackTrace();
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
