<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.demos.entity.User"%>
<%@page import="com.demos.service.IUserService"%>
<%@page import="com.demos.service.UserServiceImpl"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>servlet demo</title>
</head>
<body>
    <%
    String id = request.getParameter("id");
    IUserService userService = new UserServiceImpl();
    User user = userService.getUser(Integer.parseInt(id));
    %>
	<form id="formId" action="updateUser" method="post">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" id="id" name="id" value="<%=user.getId()%>"></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><input type="text" id="name" name="name" value="<%=user.getName()%>"></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><input type="text" id="age" name="age" value="<%=user.getAge()%>"></td>
			</tr>
			<tr>
				<td><input type="submit" value="修改" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
