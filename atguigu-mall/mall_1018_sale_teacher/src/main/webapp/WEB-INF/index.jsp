<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="shortcut icon" type="image/icon" href="image/jd.ico">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function b() {
	}
</script>
<title>硅谷商城</title>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include> 
	<div class="top_img">
		<img src="images/top_img.jpg" alt="">
	</div>
	<jsp:include page="searchArea.jsp"></jsp:include>
	<jsp:include page="classList.jsp"></jsp:include>
	
	<div class="banner">
		<div class="ban">
			<img src="images/banner.jpg" width="980" height="380" alt="">
		</div>
	</div>
</body>
</html>