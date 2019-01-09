package com.demos.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录
 * @author lilizhao
 *
 */
public class LoginServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name =request.getParameter("name");
		String password =request.getParameter("password");
		HttpSession session = request.getSession();
		if ("admin".equals(name) && "123456".equals(password)) {
			session.setAttribute("name", name);
			request.getRequestDispatcher("success.jsp").forward(request,response);
//			response.sendRedirect("");
		} else {
			request.setAttribute("msg", "用户名和密码错误！！！");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
	
}
