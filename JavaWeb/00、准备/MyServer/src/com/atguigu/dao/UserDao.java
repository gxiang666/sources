package com.atguigu.dao;

import java.util.Map;

import com.atguigu.bean.User;

/**
 * 约束对用户表的操作
 * @author Administrator
 *
 */
public interface UserDao {
	/**
	 * 根据账号查询用户
	 * 	-返回值不为空代表查询成功
	 * @return
	 */
	public User getUserByUsername(Map<String, String> paramsMap);
}
