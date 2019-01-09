<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core' %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>servlet demo</title>
</head>
<body>
	<div style="font-size: 20px; font-weight: bold;">欢迎${name }登录</div>
	<div>
		<a href="success.jsp">返回</a>
	</div>

	<table style="width: 100%;" border="1">
		<tr align="center" style="font-size: 16px; font-weight: bold;">
			<td>ID</td>
			<td>姓名</td>
			<td>年龄</td>
		</tr>
		<c:forEach items="${userList }" var="user" varStatus="index">
			<tr>
				<td>${user.id }</td>
				<td>${user.name }</td>
				<td>${user.age}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
