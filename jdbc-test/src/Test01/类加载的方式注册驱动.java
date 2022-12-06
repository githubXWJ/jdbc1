package Test01;

import java.sql.SQLException;

/**
 * @date 2022/10/23 - 20:49
 */
public class 类加载的方式注册驱动 {
    public static void main(String[] args) {
        //            java.sql.Driver driver = new com.mysql.jdbc.Driver();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
