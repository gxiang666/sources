package com.demos.service;

import java.util.List;

import com.demos.dao.IUserDao;
import com.demos.dao.UserDaoImpl;
import com.demos.entity.User;

public class UserServiceImpl implements IUserService {
	
	private IUserDao userDao = new UserDaoImpl();

	@Override
	public List<User> listUser() {
		return userDao.listUser();
	}

	@Override
	public User getUser(Integer id) {
		return userDao.getUser(id);
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void deleteUser(Integer id) {
		userDao.deleteUser(id);
	}

}
