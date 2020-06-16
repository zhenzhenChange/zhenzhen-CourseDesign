<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Register</title>

<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

</head>
<body>
	<form action="" align="center">
	<div style="margin:20px 0;"align="center"></div>
	<div class="easyui-panel" title="用户注册" style="width:400px;padding:10px 60px 20px 60px" align="center">
		<table cellpadding="5">
			<tr>
				<td>账号:</td>
				<td><input class="easyui-validatebox textbox" data-options="required:true,validType:'length[3,10]'"></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input class="easyui-validatebox textbox" data-options="required:true,validType:'email'"></td>
			</tr>
			<tr>
				<td>生日:</td>
				<td><input class="easyui-datebox textbox"></td>
			</tr>
			<tr>
				<td>地址:</td>
				<td><input class="easyui-validatebox textbox" data-options="required:true,validType:'url'"></td>
			</tr>
			<tr>
				<td>电话:</td>
				<td><input class="easyui-validatebox textbox" data-options="required:true"></td>
			</tr>
		</table>
	</div>
	<style scoped="scoped">
		.textbox{
			height:20px;
			margin:0;
			padding:0 2px;
			box-sizing:content-box;
		}
	</style>
</body>
</form>
</html>