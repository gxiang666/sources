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

<title>硅谷商城</title>
</head>
<body>
	spu信息添加${spu.flbh1}|${spu.flbh2}|${spu.pp_id}
	<hr>
	<form action="spu_add.do" enctype="multipart/form-data" method="post">
		<input type="hidden" name="flbh1" value="${spu.flbh1}"/>
		<input type="hidden" name="flbh2" value="${spu.flbh2}"/>
		<input type="hidden" name="pp_id" value="${spu.pp_id}"/>
		商品名称：<input name="shp_mch" type="text" /><br>
		商品描述：<textarea  name="shp_msh" rows="10" cols="50"></textarea><br>
		商品图片：<br>
		<div id ="d_0" style="float:left;margin-left:10px;border:1px red solid;">
			<input id="file_0" type="file" name="files" style="display:none;" onChange="replace_image(0)"/>
			<img id="image_0" onclick="click_image(0)" style="cursor:pointer;" src="image/upload_hover.png" height="100px" width="100px"/>
		</div>

		<input type="submit" value="提交"/>
	</form>
	
	
	<script type="text/javascript">
	function click_image(index){
		$("#file_"+index).click();

	}
	
	function replace_image(index){
		// 获得图片对象
		var blob_image = $("#file_"+index)[0].files[0];
		var url = window.URL.createObjectURL(blob_image);
		
		// 替换image
		$("#image_"+index).attr("src",url);
		
		//表单选择器
		var length = $(":file").length;
		
		if((index+1)==length){
			// 用户选择的是最后一张图片
			add_image(index);
		}
	
	}
	
	function add_image(index){
		var a = '<div id ="d_'+(index+1)+'" style="float:left;margin-left:10px;border:1px red solid;">';
		var b = '<input id="file_'+(index+1)+'" type="file" name="files" style="display:none;" onChange="replace_image('+(index+1)+')"/>'
		var c = '<img id="image_'+(index+1)+'" onclick="click_image('+(index+1)+')" style="cursor:pointer;" src="image/upload_hover.png" width="100px" height="100px"/>'
		var d = '</div>';
		$("#d_"+index).after(a+b+c+d);
	}
</script>
</body>
</html>