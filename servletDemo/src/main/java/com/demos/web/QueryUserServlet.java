package com.demos.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demos.entity.User;
import com.demos.service.IUserService;
import com.demos.service.UserServiceImpl;

/**
 * 查询用户列表
 * @author lilizhao
 *
 */
public class QueryUserServlet extends HttpServlet{
	
	private IUserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		List<User> userList = userService.listUser();
		request.setAttribute("userList", userList);
		request.getRequestDispatcher(page).forward(request,response);
	}
	
}
