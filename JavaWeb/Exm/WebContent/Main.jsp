<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录图书管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>
<script language="javascript">
	$(function() {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});
		$(window).resize(function() {
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 692) / 2
			});
		})
	});
</script>
</head>

<body
	style="background-color: #df7611; background-image: url(images/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">
	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>
	<div class="logintop">
		<span>欢迎来到图书管理平台</span>
	</div>
	<div class="loginbody">
		<br> <br> <br> <br> <br> <br> <br>
		<br>
		<div class="loginbox">
			<form action="login.do" method="post">
				<ul>
					<li><input name="uname" type="text" class="loginuser"
						placeholder="账号" /></li>
					<li><input name="pawd" type="password" class="loginpwd"
						placeholder="密码" /></li>
					<li><input name="login" type="submit" class="loginbtn"
						value="登录" /> <input name="" type="button" class="loginbtn2"
						value="注册" onclick="javascript:window.location='Register.jsp'" /></li>
				</ul>
			</form>
		</div>
	</div>
	<div class="loginbm">@2017070030429</div>
</body>
</html>