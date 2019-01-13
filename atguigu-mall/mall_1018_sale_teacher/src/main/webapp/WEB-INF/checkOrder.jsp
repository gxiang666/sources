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

<title>订单支付</title>
<link rel="stylesheet" type="text/css" href="css/css.css"/>		
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function order_submit(){
		$("#order_form").submit();
	}
	
	function show_address(str){
		$("#show_address").html(str);
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="searchArea.jsp"></jsp:include>

	
	<div class="message">
			<div class="msg_title">
				收货人信息
			</div>
			<form action="save_order.do" id="order_form">
				<c:forEach items="${list_address}" var="addr">
					<div class="msg_addr">
							<span class="msg_left">
							   ${addr.shjr} 
							</span>
						<span class="msg_right">
							${addr.yh_dz}<input onclick="show_address('${addr.yh_dz}${addr.shjr}${addr.lxfsh}')" type="radio" name="id" value="${addr.id}"/>
						</span>
					</div>
				</c:forEach>
			</form>
			<span class="addrs">查看更多地址信息</span>
			<div class="msg_line"></div>
		
			<div class="msg_title">
				送货清单
			</div>
			<c:forEach items="${order.list_flow}" var="flow" >
			
					<div style="margin-top:20px;">
					<c:forEach items="${flow.list_info}" var="info" varStatus="index">
					<div class="msg_list">
						<div class="msg_list_left">
							<c:if test="${index.index==0}">
								配送方式：${flow.mqdd}
								<div class="left_title">
									${flow.psfsh} 
								</div>
							</c:if>
						</div>
						<div class="msg_list_right">
							<div class="msg_img">
								<img width="80px"  height="80px" src="upload/image/${info.shp_tp}"/>
							</div>
							<div class="msg_name">
								${info.sku_mch}
							</div>
							<div class="msg_price">
								￥${info.sku_jg}
							</div>
							<div class="msg_mon">
								*${info.sku_shl}
							</div>
							<div class="msg_state">
								有货
							</div>
						</div>
					</div>
					
					</c:forEach>
							
					</div>
			</c:forEach>			
			<div class="msg_line"></div>
		
			<div class="msg_sub">
				<div class="msg_sub_tit">
					应付金额：
					<b>￥${order.zje}</b>
				</div>
				<div class="msg_sub_adds">
					寄送至 ： <span id="show_address"></span>
				</div>
				
				<button class="msg_btn" style="cursor:pointer;" onclick="order_submit()">提交订单</button>
				
				
			</div>
		
		</div>
</body>
</html>