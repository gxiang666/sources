<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<link rel="shortcut icon" type="images/icon" href="images/jd.ico">
<link rel="stylesheet" type="text/css" href="css/css.css"/>
<script type="text/javascript">
	function a(){}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>硅谷商城</title>
</head>
<body>
	<div class="top">
		<div class="top_text">
			<a href="javascript:;">用户注册</a>
		</div>
	</div>
	<div class="searchBac">
		<div class="search logo"><img src="images/logo.png" alt=""></div>
	</div>
	<div class="comment">
		<div class="comment_finish">
			订单${trade_no}支付已完成！
		</div>
	</div>
</body>
</html>