<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>

<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function b(){
		var form = $("#loginForm").serialize();
		
		$.get("login.do",form,function(data){
			alert(data);
		});
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	异步登陆表单测试
	<hr>
	<form id="loginForm">
		username:<input type="text" name="yh_mch"/><br>
		password:<input type="text" name="yh_mm"/><br>
		password:<input type="text" name="list_attr[0].shxm_id"/><br>
		login:<input type="button" onclick="b()" value="test"/><br>
	</form>
	
</body>
</html>