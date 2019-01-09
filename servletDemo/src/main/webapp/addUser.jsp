<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>servlet demo</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<form id="formId" action="addUser" method="post">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" id="id" name="id"></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><input type="text" id="name" name="name"></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><input type="text" id="age" name="age"></td>
			</tr>
			<tr>
				<td><input type="submit" value="保存" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
