<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付宝即时到账交易接口</title>
</head>
<body>
<form id="submitform" name="submitform" action="${URL}" method="${METHOD}"   >
<c:forEach var="item" items="${MAP}">  
   <input type="hidden" name="${item.key}" value="${item.value}"/>
 </c:forEach> 
</form>
 
<script>document.forms['submitform'].submit();</script>  
 
</body>
</html>