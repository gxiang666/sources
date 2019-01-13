<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function b(){}
</script>
<title>硅谷商城</title>
</head>
<body>
	<h6>最新加入的商品</h6>
	
	<c:forEach items="${list_cart}" var="cart">
		<div class="one">
			<img src="upload/image/${cart.shp_tp}" width="80px;" />
			<span class="one_name">
				${cart.sku_mch}
			</span>
			<span class="one_prece">
				<b>￥${cart.sku_jg}</b><br />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除
			</span>
		</div>
	</c:forEach>
	<div class="gobottom">
		共<span>2</span>件商品&nbsp;&nbsp;&nbsp;&nbsp;
		共计￥<span>20000</span>
		<button class="goprice">去购物车</button>
	</div>
</body>
</html>