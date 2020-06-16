<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.exm.model.Book"
	import="java.util.ArrayList,java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
</head>
<form action="FindServlet">
	<table align="center">
		<tr align="center">
			<td><input type="text" name="selectname" style="width: 138px"></td>
			<td><input type="submit" value="查询"></td>
		</tr>
	</table>
</form>
<form align="center" action="ListServlet" method="post">
	<body
		style="background-image: url(images/1544196958784.jpg); background-repeat: no-repeat; background-position: center top; overflow: hidden;">
		<table
			style="background-color: gray; border-collapse: collapse; border-color: blue;"
			border="1" align="center">
			<tr align="center">
				<td>书号</td>
				<td>书名</td>
				<td>作者</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<%-- <c:forEach items="${list}" var="item">
				<tr>
					<td>${item.isbn }</td>
					<td>${item.name }</td>
					<td>${item.author }</td>
					<td>${item.available }</td>
				</tr>
			</c:forEach> --%>
			<%
				List<Book> list = (List<Book>) request.getAttribute("list");
					if (list == null || list.size() < 1) {
						out.print("没有数据!");
					} else {
						for (Book book : list) {
			%>
			<tr align="center">
				<td><%=book.getIsbn()%></td>
				<td><%=book.getName()%></td>
				<td><%=book.getAuthor()%></td>
				<td><%=book.getAvailable()%></td>
				<td><a
					href="Update.jsp?isbn=<%=book.getIsbn()%>&name=<%=book.getName()%>&author=<%=book.getAuthor()%>&available=<%=book.getAvailable()%>"
					style="text-decoration: none;">修改</a> <a
					href="DeleteServlet?isbn=<%=book.getIsbn()%>"
					style="text-decoration: none;">删除</a></td>
			</tr>
			<%
				}
				}
			%>
		</table>
		<p align="center">
			<a href="Manager.jsp"
				style="text-decoration: none; background-color: gray;">返回主页</a>
		</p>
	</body>
</form>
</html>