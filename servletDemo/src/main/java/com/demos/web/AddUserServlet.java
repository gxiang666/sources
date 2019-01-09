package com.demos.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demos.entity.User;
import com.demos.service.IUserService;
import com.demos.service.UserServiceImpl;

/**
 * 添加用户
 * @author lilizhao
 *
 */
public class AddUserServlet extends HttpServlet{
	
	private IUserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		User user = new User();
		user.setId(Integer.parseInt(id));
		user.setName(name);
		user.setAge(Integer.parseInt(age));
		
		userService.saveUser(user);
		
		List<User> userList = userService.listUser();
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("user.jsp").forward(request,response);
	}
	
}
