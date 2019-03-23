package com.atguigu.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	//在项目启动时 初始化一个数据库连接池
	private static DataSource source = new ComboPooledDataSource("webDataSource");
	/**
	 * 面向接口编程的优点：
	 * 		1、传参
	 * 		2、程序的扩展
	 */
	//用来管理线程对应的数据库连接的集合
	private static Map<Thread, Connection> map = new ConcurrentHashMap<Thread, Connection>();
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	/**
	 * 系统提供了一个类，帮助我们为当前的线程管理对象
	 * ThreadLocal<T>：不是一个线程对象，只是线程对象的本地管理对象
	 * 		内部维护了一个ConcurrentHashMap<Thread,Object>
	 * 		方法：一个线程对象只能绑定一个数据
	 * 			void set(T t);  给线程绑定对象的方法
	 * 			T get(); 获取当前线程绑定对象的方法  
	 * 			void remove(); 默认移除当前线程对象绑定的对象
	 * 			
	 */
	//从ThredLocal中获取当前线程对象绑定的数据库连接对象
	public static Connection getConn() {
		Connection conn = local.get();
		if(conn==null) {
			try {
				conn = source.getConnection();
				//第一次创建连接时，将线程对象和数据库连接绑定
				local.set(conn);
			} catch (SQLException e) {
			}
		}
		return conn;
	}
	//移除当前线程绑定的数据库连接并释放连接
	public static void releaseConn() {
		Connection conn = local.get();
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//移除当前线程绑定的数据
		local.remove();
	}
	
	
	/*private static Connection conn = null;
	*//**
	 * 获取数据库连接的方法
	 * @return
	 *//*
	public static Connection getConnection() {
		if(conn==null) {
			//从数据库连接池中获取数据库连接
			try {
				conn = source.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}*/
	public static Connection getConnection() {
		//每个线程第一次调用此方法创建一个数据库连接，和线程绑定;此线程执行到后面时可以获取和当前线程绑定的数据库连接
		//从map中取出当前线程对应的数据库连接
		Connection conn = map.get(Thread.currentThread());
		//如果conn==null 代表当前线程第一次调用此方法，新建数据库连接并绑定
		if(conn==null) {
			try {
				//获取当前的线程对象
				Thread thread = Thread.currentThread();
				//创建一个数据库连接
				conn = source.getConnection();
				//当前线程和数据库连接绑定   Map<Thread,Connection>
				map.put(thread, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		//如果conn！=null 当前线程已经绑定过数据库连接了，可以直接返回
		return conn;
	}
	
	
	/**
	 * 关闭数据库连接的方法
	 * @param conn
	 */
	/*public static void closeConnection(Connection conn) {
		//当线程执行结束，释放和当前线程绑定的数据库连接
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}*/
	public static void closeConnection() {
		//释放和当前线程绑定的数据库连接
		Connection conn = map.get(Thread.currentThread());
		//关闭连接
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//移除map中当前线程的数据
		map.remove(Thread.currentThread());
	}
}
