<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manager</title>
<script language="JavaScript" type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$("#out").click(function() {
			var flag = window.confirm("你真的要退出吗？");
			if (flag) {
				window.top.location.href = "userOut?oper=out";
			}
		})
	})
</script>
</head>

<form action="LoginServlet" method="post">
	<div align="center">
		<table align="center">
			<tr align="center" style="background-color: gray;">
				<td><span>"${user.id}"-欢迎您</span></td>
			</tr>

		</table>
	</div>
</form>
<div align="center">
	<table align="center">
		<tr align="center">
			<td><a href="OutServlet" style="text-decoration: none;" id="out">退出</a></td>
		</tr>
	</table>
</div>
<hr>
<br>
<body
	style="background-color: #df7611; background-image: url(images/海浪.jpg); background-repeat: no-repeat; background-position: center top; overflow: hidden;">
	<form action="ListServlet" method="post">
		<div align="center">
			<input type="submit" value="查看书籍">
			<!-- <a href="List.jsp" style="text-decoration: none;">查看书籍</a> -->
			<hr width="65px">
			<a href="Add.jsp" style="text-decoration: none;">添加书籍</a>
		</div>
	</form>
</body>
</html>