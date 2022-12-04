package Test01;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * @date 2022/10/25 - 15:12
 */
public class 处理查询结果集 {
    public static void main(String[] args) {
        //使用资源绑定器绑定资源
        ResourceBundle bundle = ResourceBundle.getBundle("sources/jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            //1.注册驱动
            Class.forName(driver);
            //2.获取连接
            conn = DriverManager.getConnection(url,user,password);
            //System.out.println("Connection="+conn);
            //3.获取数据库操作对象(Statement专门执行SQL语句的)
            stmt = conn.createStatement();
            //4.执行SQL语句
            String sql = "select empno,ename,sal from emp order by sal asc";
            //执行DQL语句(处理查询结果集)
            rs = stmt.executeQuery(sql);
            //5处理查询结果集
//            while (rs.next()){
//                String empno = rs.getString(1);
//                String ename = rs.getString(2);
//                String sall = rs.getString(3);
//                System.out.println("编号:"+empno+"\t姓名:"+ename+"\t工资:"+sall);
//            }
            while (rs.next()){
                String empno = rs.getString("empno");
                String ename = rs.getString("ename");
                String sall = rs.getString("sal");
                System.out.println("编号:"+empno+"\t姓名:"+ename+"\t工资:"+sall);
            }

        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
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
