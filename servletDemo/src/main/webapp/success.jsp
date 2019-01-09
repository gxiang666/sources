<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>servlet demo</title>
</head>
<body>
	<div style="font-size: 20px;font-weight: bold;">
	   欢迎<%=session.getAttribute("name")%>登录
	</div>
	<div>
		<a href="queryUserList?page=user.jsp">用户列表</a>
		
		<a href="queryUserList?page=user2.jsp">用户列表2</a>
	   
	</div>
</body>
</html>
