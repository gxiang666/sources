<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script  src="static/jquery/jquery-3.1.0.js" ></script>
<link href="static/bs/css/bootstrap.min.css" rel="stylesheet" />
<script  src="static/bs/js/bootstrap.min.js"   ></script>
 
</head>


 
<body>
   <br>
   <div class="container">
    
		<div class="row">
		    <div id="alertdiv" class="col-md-12">
		         <div class="alert alert-success" role="alert">
		          <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			      <h4>成功！</h4>
			       <p> 
 						支付成功！<span id="show"></span> 
			       </p>
			      <p>
		           <button type="button" class="btn btn-success" id="return_btn">跳回商家页面</button>
		          </p>
		         </div>
		      </div>
         </div>
      </div>
</body>
<script type="text/javascript"> 
var t=10;//设定跳转的时间 
setInterval("refer()",1000); //启动1秒定时 
function refer(){  
    if(t==0){ 
        location="${requestScope.busi_return_url}"; //#设定跳转的链接地址 
    } 
    $("#show").text(t+"秒后跳转到商户"); // 显示倒计时 
    t--; // 计数器递减 
} 

$(function(){
	$("#return_btn").click( function () { location="${requestScope.busi_return_url}" });
});


</script>
</html>