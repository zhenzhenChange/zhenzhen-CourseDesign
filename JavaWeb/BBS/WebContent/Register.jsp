<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link href="BBS.css" rel="stylesheet" type="text/css" />
<style type="text/css">
html {
	width: 100%; /*设置页面宽度占据整个页面*/
	height: 100%; /*设置页面高度占据整个页面*/
	background-image: url("images/星空动态图1.GIF");
	background-position: bottom;
}

form {
	width: 700px; /*设置form表单的宽度*/
	height: 650px; /*设置form表单的高度*/
	margin-left: auto; /*通过这种方式使form表单左右居中*/
	margin-right: auto;
	background: gray;
	opacity: 0.8; /*设置form表单的背景颜色*/
}

.form-table {
	border-collapse: collapse; /*设置表格的单元格之间没有间隔，由于会隐藏边框，所以这一项设置的意义不大*/
}

.label-td {
	text-align: right; /*让密码、用户名这些字样左对齐*/
}

.label-p { /*设置密码、用户名这些字样的样式、边距等*/
	font-family: cursive;
	margin-top: 10px;
	margin-bottom: 10px;
}

.input-td { /*留作注册页面的参考*/
	width: 240px;
}

.tip-p { /*留作注册页面的参考*/
	margin-top: 10px;
	margin-bottom: 10px;
	font-size: 15px;
	color: red;
	opacity: 0.8;
	text-align: left;
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
	background-color: #FF7F00;
	border-width: 0px;
	width: 244px;
	padding-top: 9px;
	padding-bottom: 9px;
	margin-top: 30px;
	margin-bottom: 20px;
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

#tdiv {
	text-align: center;
}
</style>
<script src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">

	function regform() {
		var account = document.registerform.account.value;
		var upassword = document.registerform.upassword.value;
		var Pwd = document.registerform.Pwd.value;
		var uname = document.registerform.uname.value;
		var birth = document.registerform.birth.value;
		var hobbies = document.registerform.hobbies.value;
		var resident = document.registerform.resident.value;
		var degree = document.registerform.degree.value;
		var position = document.registerform.position.value;
		var tele = document.registerform.tele.value;
		var email = document.registerform.email.value;

		if (account.length < 6) {
			if (account == null || account == "") {
				alert("账号不能为空！");
				return false;
			} else {
				alert("账号不能少于6个字符！");
				return false;
			}
		}
		if (upassword.length < 6) {
			if (upassword == null || upassword == "") {
				alert("密码不能为空！");
				return false;
			} else {
				alert("密码不能少于6个字符！");
				return false;
			}
		}
		if (Pwd != upassword) {
			alert("两次密码不相同！");
			return false;
		}
		if (uname == null || uname == "") {
			alert("请输入姓名！");
			return false;
		}
		if (birth == null || birth == "") {
			alert("请填写出生日期！");
			return false;
		}
		if (hobbies == null || hobbies == "") {
			alert("请选择兴趣爱好！");
			return false;
		}
		if (resident == null || resident == "") {
			alert("请选择居住地区！");
			return false;
		}
		if (degree == null || degree == "") {
			alert("请选择学历！");
			return false;
		}
		if (position == null || position == "") {
			alert("请选择职业！");
			return false;
		}
		if (tele.length < 11) {
			if (tele == null || tele == "") {
				alert("请输入手机号！");
				return false;
			} else {
				alert("手机号不能少于11个字符！");
				return false;
			}
		}
		if (email == null || email == "") {
			alert("请填写邮箱！");
			return false;
		}
	}
</script>
</head>
<body>
	<form method="post" action="RegisterServlet" name="registerform" onsubmit="return regform()">
		<p class="top-margin2"></p>
		<p class="title-p" align="left">注册</p>
		<div id="tdiv">
			<table  border="0" align="center" class="form-table">
				<tr>
					<td class="label-td"><p class="label-p">账&nbsp&nbsp&nbsp&nbsp号</p></td>
					<td class="input-td" align="center">
						<input id="id" class="input-name" name="account" type="text">
					</td>
					<td class="tip-td" align="center"><p class="tip-p">账号长度不少于6个字符</p></td>
				</tr>
				<tr>
					<td class="label-td"><p class="label-p">密&nbsp&nbsp&nbsp&nbsp码</p></td>
					<td class="input-td" align="center">
						<input id="name" class="input-name" name="upassword" type="password">
					</td>
					<td class="tip-td" align="center"><p class="tip-p">密码长度不少于6个字符</p></td>
				</tr>
				<tr>
					<td class="label-td"><p class="label-p">重复密码</p></td>
					<td class="input-td" align="center">
						<input id="name" class="input-name" name="Pwd" type="password">
					</td>
					<td class="tip-td" align="center"><p class="tip-p">请再次输入密码</p></td>
				</tr>
				<tr>
					<td class="label-td"><p class="label-p">姓&nbsp&nbsp&nbsp&nbsp名</p></td>
					<td class="input-td" align="center">
						<input id="name" class="input-name" name="uname" type="text">
					</td>
					<td class="tip-td" align="center"><p class="tip-p">请输入真实姓名</p></td>
				</tr>
				<tr>
					<td class="label-td"><p class="label-p">性&nbsp&nbsp&nbsp&nbsp别</p></td>
					<td class="input-td">
						<input id="name" class="input-name" name="gender" type="radio" value="男" checked="true">男
						<input id="name" class="input-name" name="gender" type="radio" value="女">女
					</td>
					<td class="tip-td" align="center"><p class="tip-p">请选择性别</p></td>
				</tr>
				<tr>
					<td class="label-td"><p class="label-p">出生年月</p></td>
					<td class="input-td" align="center">
						<input id="name" class="input-name" name="birth" type="date">
					</td>
					<td class="tip-td" align="center"><p class="tip-p">请选择出生年月</p></td>
				</tr>
				<tr>
					<td class="label-td"><p class="label-p">兴趣爱好</p></td>
					<td class="input-td" align="center">
						<select name="hobbies">
								<option value="">&#60请选择&#62</option>
								<option value="游戏">游戏</option>
								<option value="音乐">音乐</option>
								<option value="交流">交流</option>
								<option value="体育">体育</option>
						</select>
					</td>
					<td class="tip-td" align="center"><p class="tip-p">请选择兴趣爱好</p></td>
				</tr>
				<tr>
					<td class="label-td"><p class="label-p">所处地区</p></td>
					<td class="input-td" align="center">
						<select name="resident">
								<option value="">&#60请选择&#62</option>
								<option value="东部">东部</option>
								<option value="西部">西部</option>
								<option value="南部">南部</option>
								<option value="北部">北部</option>
						</select>
					</td>
					<td class="tip-td" align="center"><p class="tip-p">请选择地区</p></td>
				</tr>
				<tr>
					<td class="label-td"><p class="label-p">学&nbsp&nbsp&nbsp&nbsp历</p></td>
					<td class="input-td" align="center">
						<select name="degree">
								<option value="">&#60请选择&#62</option>
								<option value="本科">本科</option>
								<option value="专科">专科</option>
								<option value="研究生">研究生</option>
								<option value="博士">博士生</option>
								<option value="高中">高中</option>
								<option value="其他">其他</option>
						</select>
					</td>
					<td class="tip-td" align="center"><p class="tip-p">请选择学历</p></td>
				</tr>
				<tr>
					<td class="label-td"><p class="label-p">职&nbsp&nbsp&nbsp&nbsp业</p></td>
					<td class="input-td" align="center">
						<select name="position">
								<option value="">&#60请选择&#62</option>
								<option value="学生">学生</option>
								<option value="社会人士">社会人士</option>
								<option value="教师">教师</option>
								<option value="工作">工作</option>
								<option value="其他">其他</option>
						</select>
					</td>
					<td class="tip-td" align="center"><p class="tip-p">请选择职业</p></td>
				</tr>
				<tr>
					<td class="label-td"><p class="label-p">电&nbsp&nbsp&nbsp&nbsp话</p></td>
					<td class="input-td" align="center">
						<input id="name" class="input-name" name="tele" type="text">
					</td>
					<td class="tip-td" align="center"><p class="tip-p">请输入电话</p></td>
				</tr>
				<tr>
					<td class="label-td"><p class="label-p">电子邮箱</p></td>
					<td class="input-td" align="center">
						<input id="name" class="input-name" name="email" type="text">
					</td>
					<td class="tip-td" align="center"><p class="tip-p">请输入电子邮箱</p></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input class="login-btn" type="submit" value="提交">
						&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						<input class="login-btn" type="reset" value="清空">
					</td>
					<td></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>