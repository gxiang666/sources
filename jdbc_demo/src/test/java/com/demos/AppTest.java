package com.demos;

import java.util.List;

import com.demos.entity.User;
import com.demos.service.IUserService;
import com.demos.service.UserServiceImpl;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
	IUserService userService = new UserServiceImpl();
   
	/**
	 * 测试查询用户列表
	 */
	public void testListUser()
    {
         List<User> listUser = userService.listUser();
         for(User user:listUser) {
        	 System.out.println(user);
         }
    }
	/**
	 * 测试根据id获取用户
	 */
	public void testGetUser()
	{
		User user = userService.getUser(9);
		System.out.println(user);
	}
	
	/**
	 * 测试添加用户
	 */
	public void testSaveUser()
    {
         User user = new User();
         user.setId(10);
         user.setName("aaa");
         user.setAge(20);
         userService.saveUser(user);
    }
	/**
	 * 测试修改用户
	 */
	public void testUpdateUser()
	{
		User user = new User();
		user.setId(10);
		user.setName("bbbbb");
		user.setAge(25);
		userService.updateUser(user);
	}
	/**
	 * 测试删除用户
	 */
	public void testDeleteUser()
	{
		userService.deleteUser(10);
	}
    
	
}
