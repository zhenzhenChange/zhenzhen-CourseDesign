<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style type="text/css">
html {
	width: 100%;
	height: 100%;
	background-image: url("images/星空动态图2.gif");
	background-size: cover;
}

form {
	width: 500px;
	height: 380px;
	margin-left: auto;
	margin-right: auto;
	background: rgba(225, 225, 225, 0.85);
}

.form-table {
	border-collapse: collapse;
}

.label-td {
	text-align: left;
}

.label-p {
	font-family: 楷体;
	font-weight: bold;
	margin-top: 10px;
	margin-bottom: 10px;
}

.input-td {
	width: 240px;
}

.tip-p {
	margin-top: 10px;
	margin-bottom: 10px;
	font-size: 10pt;
	color: grey;
}

.tip-p1 {
	margin-top: 10px;
	margin-bottom: 10px;
	font-size: 10pt;
	color: red;
}

.tip-td {
	width: 280px;
}

input[type=text], input[type=password], input[type=date], select {
	padding-top: 3px;
	padding-bottom: 3px;
	width: 220px;
	height: 25px;
	margin-left: -15px;
}

.btn-submit {
	margin-left: -18px;
	border-width: 0px;
	width: 222px;
	padding-top: 9px;
	padding-bottom: 9px;
	margin-top: 30px;
	margin-bottom: 20px;
	background-color: #FF7F00;
}

.btn-submit:hover {
	cursor: pointer;
	background-color: gray;
}

.top-margin {
	height: 100px;
}

.top-margin2 {
	height: 5%;
}

.title-p {
	font-family: 华文行楷;
	font-size: 25px;
	text-align: center;
}

.btn-reset {
	border-width: 0px;
	width: 244px;
	padding-top: 9px;
	padding-bottom: 9px;
	margin-top: 30px;
	margin-bottom: 20px;
	background-color: #C1FFC1;
}
</style>
<link href="BBS.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

	function logform() {
		var account = document.loginform.account.value;
		var upassword = document.loginform.upassword.value;
		if (account == null || account == "") {
			alert("请输入账号！");
			return false;
		}
		if (upassword == null || upassword == "") {
			alert("请输入密码！");
			return false;
		}
	}
</script>
</head>
<body>
	<p class="top-margin">&nbsp</p>
	<form method="post" action="LoginServlet" name="loginform" onsubmit="return logform()">
		<p class="top-margin2">&nbsp</p>
		<p class="title-p" align="center">登录</p>
		<table border="0" align="center" class="form-table">
			<tr>
				<td class="label-td"><p class="label-p">账号</p></td>
			</tr>
			<tr>
				<td class="input-td" align="center">
					<input id="id" class="input-name" name="account" type="text" placeholder="请输入账号">
				</td>
			</tr>
			<tr>
				<td class="label-td"><p class="label-p">密码</p></td>
			</tr>
			<tr>
				<td class="input-td" align="center">
					<input id="pwd" class="input-name" name="upassword" type="password" placeholder="请输入密码">
				</td>	
			</tr>
			<tr>
				<td  align="center">
					<input class="btn-submit" type="submit" value="登录">
				</td>
			</tr>
			<tr>
				<td align="center">
					<a class="mm-a" href="Register.jsp" target="indexiframe">去注册</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>