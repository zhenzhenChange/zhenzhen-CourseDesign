<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bbs.model.*" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ViewMessage</title>
<link href="BBS.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%
		String num = request.getParameter("num");
		String uname = request.getParameter("uname");
		String theme = request.getParameter("theme");
		String mold = request.getParameter("mold");
		String context = request.getParameter("context");
		String nowtime = request.getParameter("nowtime");

		request.setAttribute("num", num);
		request.setAttribute("uname", uname);
		request.setAttribute("theme", theme);
		request.setAttribute("mold", mold);
		request.setAttribute("context", context);
		request.setAttribute("nowtime", nowtime);
	%>
	<%
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	%>
	<%
		UsersInfo user = (UsersInfo) session.getAttribute("user");
		if (user != null) {
	%>
	<%
		} else {
			PrintWriter writer = response.getWriter();
			writer.println("<script type='text/javascript'>" + "alert('请先登录！');" + "location.href='Login.jsp';</script>");
			writer.close();
		}
	%>
	<form action="ViewMessageServlet" method="post">
		<div id="view-div">
			<label>主题：</label><input class="sessinp" name="theme" value="${theme}" readonly="readonly"><br>
			<label>作者：</label><input class="sessinp" name="uname" value="${uname}" readonly="readonly"><br>
			<label>类型：</label><span>[${mold}]</span><br>
			<label>发布日期：</label><span>[${nowtime}]</span><br>
			<label>内容：</label><span>[${context}]</span><br>
		</div>
		<div id="teardiv">
			<textarea name="revtext" style="width: 800px;height: 100px;resize: none;" placeholder="可评论"></textarea>
			<br>
			<input type="submit" value="发表">
		</div>
			<label>当前时间：</label><input class="sessinp" name="retime" value="<%=sdf.format(date)%>" readonly="readonly">
	</form>
</body>
</html>