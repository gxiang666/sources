package com.atguigu.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.utils.JDBCUtils;

/**
 * 不会直接创建对象
 * 数据库操作的基类
 * 	提供通用的基本的增删改查方法
 * @author Administrator
 * 	使用：
 * 		UserDaoImpl extends BaseDao<User>
 *
 */
public class BaseDao<T> {
	/**
	 * dbutils提供的数据查询帮助工具类
	 */
	private QueryRunner runner = new QueryRunner();
	private Class<T> type;
	/**
	 * 在构造器中初始化type
	 * 	构造器肯定是由子类调用，子类调用时 泛型类型已经确定了，baseDao对象在创建后，type可能要使用
	 * this代表子类对象
	 */
	public BaseDao() {
		//UserDaoImpl extends BaseDao<User>
		Class<? extends BaseDao> cla = this.getClass();
		//获取BaseDao<User>   BaseDao的真实类型时参数化的type
		ParameterizedType pt = (ParameterizedType) cla.getGenericSuperclass();
		//获取泛型列表
		Type[] types = pt.getActualTypeArguments();
		type = (Class<T>) types[0];
	}
	/**
	 * 增删改的方法
	 */
	public int update(String sql,Object...params) {
		int i = 0;
		//参数1：数据库连接，参数2：sql语句，参数3：sql需要的参数列表
		Connection conn = JDBCUtils.getConnection();
		//sql语句和参数不能确定，谁调用谁处理
		try {
			i = runner.update(conn, sql, params);
		} catch (SQLException e) {
			//e.printStackTrace();
			//将编译时的异常转为运行时异常手动抛出
			throw new RuntimeException(e);
		}finally {
			//释放数据库连接
		//	JDBCUtils.closeConnection(conn);
		}
		return i;
	}
	
	/**
	 * 查询一个用户对象
	 * @param sql
	 * @param params
	 * @return
	 */
	public T getBean(String sql,Object...params) {
		Connection conn = JDBCUtils.getConnection();
		T t = null ;
		try {
			/**
			 * BeanHandler封装对象
			 * 	1、调用bean的无参构造器创建对象
			 * 	2、根据数据库查询的字段去bean中找对应的setXXX方法
			 * 	如果 javabean的字段名和数据库表字段名一样，那么beanhandler就可以找到set方法将查询的值设置给bean
			 * 	如果不一致，就找不到对应的set方法，设置失败
			 */
			t = runner.query(conn, sql, new BeanHandler<>(type), params);
		} catch (SQLException e) {
			//将编译时的异常转为运行时异常手动抛出
			throw new RuntimeException(e);
		}finally {
		//	JDBCUtils.closeConnection(conn);
		}
		return t;
	}
	/**
	 * 查询多条记录封装为对象的集合方法
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<T> getBeanList(String sql,Object...params){
		Connection conn = JDBCUtils.getConnection();
		List<T> list = null;
		try {
			list = runner.query(conn, sql, new BeanListHandler<>(type), params);
		} catch (SQLException e) {
			//将编译时的异常转为运行时异常手动抛出
			throw new RuntimeException(e);
		}finally {
		//	JDBCUtils.closeConnection(conn);
		}
		
		return list;
	}
	/**
	 * 查询数量的方法
	 * @param sql
	 * @param params
	 * @return
	 */
	public long  getCount(String sql,Object...params) {
		Connection conn = JDBCUtils.getConnection();
		long count = 0;
		try {
			//ScalarHandler:默认会将第一行第一列查询到的数据封装为对象返回
			//如果查询到的是数字，那么就封装为Long对象 ，如果是String那么久封装为string
			count = (long) runner.query(conn, sql, new ScalarHandler() , params);
		} catch (SQLException e) {
			//将编译时的异常转为运行时异常手动抛出
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConnection(conn);
		}
		return count;
	}
	
	
}
