<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.demos.entity.User"%>
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
        <a href="success.jsp">返回</a>  
        <a href="addUser.jsp">添加用户</a>  
    </div>


    
    <table style="width: 100%;" border="1">
        <tr align="center" style="font-size: 16px;font-weight: bold;">
            <td>ID</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>操作</td>
        </tr>
	<% 
		List<User> userList = (List<User>)request.getAttribute("userList");
		for(User u1: userList)
		{
	%>
		<tr>
			<td><%=u1.getId() %></td>
			<td><%=u1.getName() %></td>
			<td><%=u1.getAge() %></td>
			<td>
                <a href="updateUser.jsp?id=<%=u1.getId() %>">修改用户</a>
                <a href="deleteUser?id=<%=u1.getId() %>">删除用户</a>
            </td>
		</tr>
	<%
		}
	%>
	  </table>
</body>
</html>
