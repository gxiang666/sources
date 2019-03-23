package com.atguigu.dao.impl;

import java.util.Map;

import com.atguigu.bean.User;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.UserDao;

/**
 * user表操作的实现
 * @author Administrator
 *
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao{

	@Override
	public User getUserByUsername(Map<String, String> paramsMap) {
		String sql = "SELECT id , username , password, email FROM "+paramsMap.get("table")+" WHERE username= ?";
		return this.getBean(sql, paramsMap.get("username"));
	}


}
