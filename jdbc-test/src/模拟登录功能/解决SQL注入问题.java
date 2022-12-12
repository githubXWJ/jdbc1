package 模拟登录功能;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @date 2022/10/27 - 16:49
 * 解决SQL注入问题
 *  只要用户提供的信息不参与SQL语句的编译
 *  即使用户提供的信息含有SQL语句的关键字，但是不会参与编译不起作用
 *   要使用 java.sql.preparedStatement 预编译的数据库操作对象
 *    preparedStatement原理是预先对SQL语句的框架进行编译，然后再给SQL语句传值
 */
public class 解决SQL注入问题 {
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
        PreparedStatement ps = null;//这里使用PreparedStatement（预编译的数据库操作对象）
        ResultSet rs = null;
        try{
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","150798777278x");
            //System.out.println("Connection="+conn);
            //3.获取预编译的数据库操作对象()
            String sql = "select username,password from login1 where username=? and password=?";
            ps = conn.prepareStatement(sql);
            //给占位符传值（第一个？下标是1 第二个?下标是2）
            ps.setString(1,loginName);
            ps.setString(2,loginPwd);
            //4.执行SQL语句
            rs = ps.executeQuery();
            //执行DQL语句(处理查询结果集)

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
            if ( ps != null) {
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
