<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bbs.model.*" import="java.util.*" import="java.text.SimpleDateFormat" import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AllMessage</title>
<link href="BBS.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%
		Admins admin = (Admins) session.getAttribute("admin");
		if (admin != null) {
	%>
	<label><%=admin.getId() %></label>
	<%
		} else {
			PrintWriter writer = response.getWriter();
			writer.println("<script type='text/javascript'>" + "alert('请用管理员账号登录！');" + "location.href='Admin.jsp';</script>");
			writer.close();
		}
	%>
	<form action="LoginOutServlet" method="get">
		<section id="labeldiv">
			<input type="submit" value="退出">
		</section>
	</form>
	<form action="AllDataList.jsp" method="post">
		<div class="M-type-item">
			<table style="border-collapse: collapse; border-color: blue;align:center; border:1px;" >
				<tr>
					<td>共有${sessionScope.li}条帖子</td>
				</tr>
				<tr>
					<th>作者</th>
					<th>主题</th>
					<th>发布日期</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${Ms}" var="item">
					<tr>
						<td>${item.uname}</td>
						<td>${item.theme}</td>
						<td>${item.nowtime}</td>
						<td><a href="DeleteServlet?num=${item.num}">删除该贴</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
</body>
</html>