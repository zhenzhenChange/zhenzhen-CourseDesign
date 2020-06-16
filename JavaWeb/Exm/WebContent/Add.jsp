<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add</title>
<script src="js/jquery.min.js"></script>
<script type="text/javascript">
	function addform() {
		var isbn = document.form1.isbn.value;
		var name = document.form1.name.value;
		var author = document.form1.author.value;
		if (isbn.length < 4) {
			if (isbn == null || isbn == "") {
				alert("书号不能为空！");
				return false;
			} else {
				alert("书号不能少于4个字符！");
				return false;
			}
		}
		if (name == null || name == "") {
			alert("书名不能为空！");
			return false;
		}
		if (author == null || author == "") {
			alert("作者不能为空！");
			return false;
		}
	}
</script>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
	%>
	<form method="post" name="form1" action="AddServlet"
		style="align: center" onsubmit="return addform()">
		<table border="1" align="center">
			<tr>
				<td>书号</td>
				<td><input type="text" name="isbn"></td>
			</tr>
			<tr>
				<td>书名</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>作者</td>
				<td><input type="text" name="author"></td>
			</tr>
			<tr>
				<td>库存</td>
				<td><select name="available">
						<option value="是">是</option>
						<option value="否">否</option>
				</select></td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</body>
</html>