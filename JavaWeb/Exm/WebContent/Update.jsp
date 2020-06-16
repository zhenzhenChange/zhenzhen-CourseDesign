<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="java.sql.Connection,java.sql.DriverManager,java.sql.ResultSet,java.sql.SQLException,java.sql.Statement,java.sql.PreparedStatement" %>
<%@ page import="com.exm.model.Book" %>
<%@ page import="java.util.ArrayList,java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update</title>
<script src="js/jquery.min.js"></script>
<script type="text/javascript">
	function updateform() {
		var name = document.form2.name.value;
		var author = document.form2.author.value;
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
	<%
		String isbn = request.getParameter("isbn");
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String available = request.getParameter("available");
		request.setAttribute("isbn", isbn);
		request.setAttribute("name", name);
		request.setAttribute("author", author);
		request.setAttribute("available", available);
	%>
	<form action="UpdateServlet" name="form2" method="post" onsubmit="return updateform()">
		<table align="center">
			<tr>
				<th>更新</th>
			</tr>
		</table>
		<table border="1" align="center">
			<tr>
				<td>书号</td>
				<td><input type="text" name="isbn" value="${isbn}"
					readonly="readonly" /></td>
			</tr>
			<tr>
				<td>书名</td>
				<td><input type="text" name="name" value="${name}"></td>
			</tr>
			<tr>
				<td>作者</td>
				<td><input type="text" name="author" value="${author}"></td>
			</tr>
			<tr>
				<td>库存 <select name="available">
						<c:if test='${available == "是"}'>
							<option value="是" selected="selected">是</option>
							<option value="否">否</option>
						</c:if>
						<c:if test='${available == "否"}'>
							<option value="是">是</option>
							<option value="否" selected="selected">否</option>
						</c:if>
				</select>
				</td>
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