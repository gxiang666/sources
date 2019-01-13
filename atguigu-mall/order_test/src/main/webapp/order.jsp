<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<% String ran = System.currentTimeMillis()+""; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<link rel="shortcut icon" type="images/icon" href="images/jd.ico">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/css.css"/>		
<script type="text/javascript">
	function a(){
		$("#a").submit();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>硅谷商城</title>
</head>
<body>
	<form id="a" action="http://localhost:58080/payservice/payment"  method="POST" target="_blank">
		<input type="hidden" name="trade_no" id="out_trade_no" value="硅谷商城订单<%=ran%>">
		<input type="hidden" name="total_fee" value="0.01">
		<input type="hidden" name="busi_return_url" value="http://localhost:38080/order_test/order_success.do">
		<input type="hidden" name="busi_notify_url" value="http://localhost:38080/order_test/order_notify.do">
		<input type="hidden" name="subject" value="尚硅谷支付测试专用">
		<input type="hidden" name="body" value="即时到账测试">
	</form>
<div class="search">
			<div class="logo"><img src="images/logo.jpg" alt=""></div>
			<div class="search_on">
				<div class="se">
					<input type="text" name="search" class="lf">
					<input type="submit" class="clik" value="搜索">
				</div>
				<div class="se">
					<a href="">取暖神奇</a>
					<a href="">1元秒杀</a>
					<a href="">吹风机</a>
					<a href="">玉兰油</a>
				</div>
			</div>
		</div>
		<div class="message">
			<div class="msg_title">
				收货人信息
			</div>
			<div class="msg_addr">
				<span class="msg_left">
					姓名 北京
				</span>
				<span class="msg_right">
					北京市 昌平区 北七家镇 尚硅谷IT教育
				</span>
			</div>
			<span class="addrs">查看更多地址信息</span>
			<div class="msg_line"></div>
		
			<div class="msg_title">
				送货清单
			</div>
			<div class="msg_list">
				<div class="msg_list_left">
					配送方式
					<div class="left_title">
						硅谷快递：老佟
					</div>
				</div>
				<div class="msg_list_right">
					<div class="msg_img">
						<img src="images/msgImg.png"/>
					</div>
					<div class="msg_name">
						尚硅谷支付测试专用
					</div>
					<div class="msg_price">
						￥5999.00
					</div>
					<div class="msg_mon">
						*1
					</div>
					<div class="msg_state">
						有货
					</div>
				</div>				
			</div>			
			<div class="msg_line"></div>
		
			<div class="msg_sub">
				<div class="msg_sub_tit">
					应付金额：
					<b>￥5999.00</b>
				</div>
				<div class="msg_sub_adds">
					寄送至 ： 北京市 昌平区 北七家镇 尚硅谷IT教育    &nbsp;某某某（收）  185****1222
				</div>
				<button class="msg_btn" onclick="a()" style="cursor:pointer;">提交订单</button>
				
				
			</div>
		
		</div>
</body>
</html>