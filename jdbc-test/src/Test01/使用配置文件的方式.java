package Test01;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * @date 2022/10/23 - 20:57
 */
public class 使用配置文件的方式 {
    public static void main(String[] args) {
        //使用资源绑定器绑定资源
        ResourceBundle bundle = ResourceBundle.getBundle("sources/jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Connection conn = null;
        Statement stmt = null;
        try{
            //1.注册驱动
            Class.forName(driver);
            //2.获取连接
            conn = DriverManager.getConnection(url,user,password);
            //System.out.println("Connection="+conn);
            //3.获取数据库操作对象(Statement专门执行SQL语句的)
            stmt = conn.createStatement();
            //4.执行SQL语句
            String sql = "insert into dept2(deptno,dname,loc)values(800000,'人事部','天津')";
            //String sql = "delete from dept2 where deptno=80 or deptno=90";
            //执行DML语句
            int count = stmt.executeUpdate(sql);//count是影响数据库中的数据条数 插入一条返回1
            System.out.println(count == 1 ?"插入成功":"插入失败");


        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            if ( stmt != null) {
                try {
                    stmt.close();
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
