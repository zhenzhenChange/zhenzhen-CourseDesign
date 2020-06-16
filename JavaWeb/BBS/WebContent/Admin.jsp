<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
<style type="text/css">
html {
	width: 100%; /*设置页面宽度占据整个页面*/
	height: 100%; /*设置页面高度占据整个页面*/
	background-image: url("images/灰白星球.jpg");
}

form {
	width: 360px; /*设置form表单的宽度*/
	height: 380px; /*设置form表单的高度*/
	margin-left: auto; /*通过这种方式使form表单左右居中*/
	margin-right: auto;
	background: rgba(225, 225, 225, 0.85); /*设置form表单的背景颜色*/
}

.form-table {
	border-collapse: collapse; /*设置表格的单元格之间没有间隔，由于会隐藏边框，所以这一项设置的意义不大*/
}

.label-td {
	text-align: left; /*让密码、用户名这些字样左对齐*/
}

.label-p { /*设置密码、用户名这些字样的样式、边距等*/
	font-weight: bold;
	margin-top: 10px;
	margin-bottom: 10px;
}

.input-td { /*留作注册页面的参考*/
	width: 240px;
}

.tip-p { /*留作注册页面的参考*/
	margin-top: 10px;
	margin-bottom: 10px;
	font-size: 10pt;
	color: grey;
}

.tip-td { /*留作注册页面的参考*/
	width: 280px;
}

input[type=text], input[type=password], input[type=date], select {
	/*设置输入框的样式*/
	padding-top: 3px; /*输入内容距离输入框的上下边框的距离*/
	padding-bottom: 3px;
	width: 220px; /*输入框的宽度*/
}

.btn-submit { /*提交按钮*/
	border-width: 0px;
	width: 244px;
	padding-top: 9px;
	padding-bottom: 9px;
	margin-top: 30px;
	margin-bottom: 20px;
	background-color: #FF7F00;
}

.top-margin { /*布局使用*/
	height: 100px;
}

.top-margin2 { /*布局使用*/
	height: 5%;
}

.title-p { /*注册字样的样式*/
	font-size: larger;
	font-weight: bold;
	margin-left: 40px;
}

.btn-reset { /*清空按钮*/
	border-width: 0px;
	width: 244px;
	padding-top: 9px;
	padding-bottom: 9px;
	margin-top: 30px;
	margin-bottom: 20px;
	background-color: #C1FFC1;
}
</style>
</head>
<body>
	<p class="top-margin">&nbsp</p>
	<form action="LoginServlet" method="get">
		<p class="top-margin2">&nbsp</p>
		<p class="title-p" align="left">管理员登陆</p>
		<table border="0" align="center" class="form-table">
			<tr>
				<td class="label-td"><p class="label-p">账号</p></td>
			</tr>
			<tr>
				<td class="input-td" align="center">
					<input id="name" class="input-name" name="id" type="text">
				</td>
			</tr>
			<tr>
				<td class="label-td"><p class="label-p">密码</p></td>
			</tr>
			<tr>
				<td class="input-td" align="center">
					<input id="pwd"	class="input-name" name="pwd" type="password">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input class="btn-submit" type="submit" value="登录">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>