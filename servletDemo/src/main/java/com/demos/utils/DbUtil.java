package com.demos.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * 数据库工具类
 * @author lilizhao
 *
 */
public class DbUtil {
	public static final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useSSl=false";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";
    private static Connection conn = null;
    
    public static Connection getConnection(){
    	try {
            //1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //2. 获得数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
