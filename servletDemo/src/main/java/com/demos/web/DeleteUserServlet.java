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
 * 删除用户
 * @author lilizhao
 *
 */
public class DeleteUserServlet extends HttpServlet{
	
	private IUserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		userService.deleteUser(Integer.parseInt(id));
		
		List<User> userList = userService.listUser();
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("user.jsp").forward(request,response);
	}
	
}
