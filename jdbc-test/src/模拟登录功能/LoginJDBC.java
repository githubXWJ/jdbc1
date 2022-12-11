package 模拟登录功能;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @date 2022/10/25 - 22:30
 *
请输入用户名:fsa
请输入密码:fsa' or '1'='1
登录成功
 这能成功 是SQL注入现象

 */
public class LoginJDBC {
    public static void main(String[] args) {
        //初始化一个界面
        Map<String,String> userLoginInfo =  initUI();
        //验证用户名和密码
        boolean loginSuccess = login(userLoginInfo);
        //输出结果
        System.out.println(loginSuccess?"登录成功":"登陆失败");
    }

    /**
     * 用户登录
     * @param userLoginInfo 用户登录信息
     * @return false表示失败，true表示成功
     */
    private static boolean login(Map<String, String> userLoginInfo) {
        //打标记
        boolean loginSuccess = false;
        //JDBC 代码
        String loginName = userLoginInfo.get("loginName");
        String loginPwd = userLoginInfo.get("loginPwd");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","150798777278x");
            //System.out.println("Connection="+conn);
            //3.获取数据库操作对象(Statement专门执行SQL语句的)
            stmt = conn.createStatement();
            //4.执行SQL语句
            //String sql = "select username,password from login1 where username='"+loginName+"'" + "and password='"+loginPwd+"'";
              String sql = "select username,password from login1 where username='"+loginName+"' and password='"+loginPwd+"'";
            //执行DQL语句(处理查询结果集)
            rs = stmt.executeQuery(sql);
           // 5处理查询结果集
            if(rs.next()){
                //true说明登录成功
                loginSuccess = true;
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
        return loginSuccess;
    }

    /**
     * 初始化用户界面
     * @return 用户输入的用户名和登录密码
     */
    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);
        System.out.print("请输入用户名:");
        String loginName = s.next();
        System.out.print("请输入密码:");
        String loginPwd = s.next();
        Map<String,String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("loginName",loginName);
        userLoginInfo.put("loginPwd",loginPwd);
        return userLoginInfo;
    }


}
