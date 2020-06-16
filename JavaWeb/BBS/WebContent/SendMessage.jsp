<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bbs.model.*" import="java.io.PrintWriter" import="java.text.SimpleDateFormat" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SendMessage</title>
<link href="BBS.css" rel="stylesheet" type="text/css" />
</head>
<body>
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
			writer.println(
					"<script type='text/javascript'>" + "alert('请先登录！');" + "location.href='Login.jsp';</script>");
			writer.close();
		}
	%>
	<form method="post" action="message.message" style="margin-top: 10%;">
		<table align="center" valign="center" bgcolor="#6495ED";style="width: 40%; height: 20px;align="center" border-collapse:collapse";border="1px";>
			<tr align="left">
				<td style="height: 20px; width: 100px;">
					<font color="#F5DEB3"size="5" face="幼圆">主 题</font>
				</td>
				<td style="height: 30px; width: 100px;">
					<input name="theme"	type="text" placeholder="请填写主题">
					<input class="sessinp" name="uname" value="<%=user.getAccount()%>" readonly="readonly">
				</td>
			</tr>
			<tr align="left">
				<td style="height: 20px; width: 100px;">
					<font color="#F5DEB3" size="5" face="幼圆">类 型</font>
				</td>
				<td style="height: 30px; width: 100px;">
					<select	style="height: 20px; width: 100px;" name="mold">
							<option value="0">&#60请选择&#62</option>
							<option value="新闻类">新闻类</option>
							<option value="游戏类">游戏类</option>
							<option value="攻略类">攻略类</option>
							<option value="科普类">科普类</option>
					</select> 
					<input class="sessinp" name="nowtime" value="<%=sdf.format(date)%>"	readonly="readonly">
				</td>
			</tr>
			<tr>
				<td style="height: 20px; width: 100px;">
					<font color="#F5DEB3" size="5" face="幼圆">内 容</font>
				</td>
				<td style="height: 300px; width: 100px;">
					<textarea name="context" style="height: 300px; width: 500px; resize: none;"	placeholder="请填写内容"></textarea>
				</td>
			</tr>
			<tr align="right">
				<td colspan="2">
					<input name="sumbit" type="submit" value="发  布" style="height: 40px; width: 100px; background-color: #E1FFFF; border-width: 0px; background-color: #C1FFC1;">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>