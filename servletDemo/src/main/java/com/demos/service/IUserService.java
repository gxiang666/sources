package com.demos.service;

import java.util.List;

import com.demos.entity.User;

public interface IUserService {
	/**
	 * 获取用户列表
	 * @return
	 */
	List<User> listUser();
	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 */
	User getUser(Integer id);
	
	/**
	 * 保存用户
	 * @param user
	 */
	void saveUser(User user);
	
	/**
	 * 修改用户
	 * @param user
	 */
	void updateUser(User user);
	
	/**
	 * 根据id删除用户
	 * @param id
	 */
	void deleteUser(Integer id);
}
