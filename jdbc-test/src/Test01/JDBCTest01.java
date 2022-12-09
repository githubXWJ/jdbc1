package Test01;/**/
import java.sql.DriverManager;
import java.sql.*;
public class JDBCTest01{
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;
		try{
			//1.注册驱动
			Driver dirver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(dirver);
			//2.获取连接
			String url = "jdbc:mysql://localhost:3306/bjpowernode";//URL统一资源定位符（网络中某个资源的绝对路径）
			String user="root";
			String password = "x";
			 conn = DriverManager.getConnection(url,user,password);
			System.out.println("Connection="+conn);
			//3.获取数据库操作对象(Statement专门执行SQL语句的)
			 stmt = conn.createStatement();
			//4.执行SQL语句
			String sql = "insert into dept2(deptno,dname,loc)values(80,'人事部','天津')";
			//执行DML语句
			int count = stmt.executeUpdate(sql);//count是影响数据库中的数据条数 插入一条返回1
			System.out.println(count == 1 ?"插入成功":"插入失败");


		}catch(SQLException e){
			e.printStackTrace();
		}finally {
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














