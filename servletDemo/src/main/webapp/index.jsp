<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>servlet demo</title>
<script type="text/javascript">
	<%if(request.getAttribute("msg")!=null){%>
		alert("用户名和密码错误！！！");
 	<%}%>
</script>
</head>
<body>
	<form id="formId" action="login" method="post">
		<table>
			<tr><td>用户名</td><td><input type="text" id="name" name="name"></td></tr>
			<tr><td>密码</td><td><input type="password" id="password" name="password"></td></tr>
			<tr><td><input type="submit" value="登录" /></td></tr>
		</table>
	</form>
</body>
</html>
