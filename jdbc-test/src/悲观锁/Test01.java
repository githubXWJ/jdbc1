package 悲观锁;

import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @date 2022/10/27 - 21:17
 * 这个程勋开启一个事务，这个事务转梦进行查询，并且使用行级锁/悲观锁，锁住相关数据
 */
public class Test01 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            String sql = "select ename,job,sal from emp where job=? for update ";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"MANAGER");
            rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("ename")+","+
                        rs.getString("job")+","+
                        rs.getDouble("sal"));
            }
            //提交事务
            conn.commit();

        } catch (Exception throwables) {
            if (conn!=null){
                try {
                    //回滚事务
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
    }
}
