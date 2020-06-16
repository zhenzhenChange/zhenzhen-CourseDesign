<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.bbs.model.*" import="java.io.IOException" import="java.io.PrintWriter" import="java.sql.*" import="java.util.*" import="com.bbs.utils.JDBCUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review</title>
</head>
<body>
	<%
		UsersInfo user = (UsersInfo) session.getAttribute("user");
		if (user != null) {
	%>
	<%
		} else {
			PrintWriter writer = response.getWriter();
			writer.println(
					"<script type='text/javascript'>" + "alert('请先登录！');" + "location.href='Login.jsp';</script>");
			writer.close();
		}
	%>
	<form action="Rev" method="post">
		<section class="M-type">
			<div class="M-type-item">
				<table style="border-collapse: collapse; border-color: blue;" border="1" align="center">
					<tr>
						<td>共有${sessionScope.li}条评论</td>
					</tr>
					<tr>
						<th>作者</th>
						<th>主题</th>
						<th>评论内容</th>
						<th>评论时间</th>
					</tr>
					<c:forEach items="${rev}" var="item">
						<tr algin="center">
							<td>${item.uname}</td>
							<td>${item.theme}</td>
							<td>${item.revtext}</td>
							<td>${item.retime}</td>
							<td></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</section>
	</form>
</body>
</html>