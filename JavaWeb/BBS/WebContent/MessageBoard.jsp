<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bbs.model.*" import="java.util.*" import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MessageBoard</title>
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
	<label><%=user.getAccount()%> 欢迎您</label>
	<%
		}
	%>
	<form action="LoginOutServlet" method="post">
		<section id="labeldiv">
			<input type="submit" value="退出"><br> <label>北京时间：<%=sdf.format(date)%></label>
		</section>
	</form>
	<form action="MessageListServlet" method="post">
		<section class="M-type">
			<div>
				<div class="M-type-span">
					<p>
						<span>游戏类</span> <a href="AllMessage.jsp">更多</a>
					</p>
				</div>
				<div class="M-type-item">
					<ul class="M-type-ul">
						<c:forEach items="${Ms}" var="item">
							<c:if test="${item.mold=='游戏类'}">
								<li><a href="ViewMessage.jsp?num=${item.num}&uname=${item.uname}&theme=${item.theme}&mold=${item.mold}
								&context=${item.context}&nowtime=${item.nowtime}" target="indexiframe">${item.uname}-${item.theme}</a>-${item.nowtime}
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
		</section>
	</form>
	<section class="M-type">
		<div>
			<div class="M-type-span">
				<p>
					<span>新闻类</span> <a href="AllMessage.jsp">更多</a>
				</p>
			</div>
			<div class="M-type-item">
				<ul class="M-type-ul">
					<c:forEach items="${Ms}" var="item">
						<c:if test="${item.mold=='新闻类'}">
							<li>${item.uname}-<a
								href="ViewMessage.jsp
								?num=${item.num}&uname=${item.uname}&theme=${item.theme}&mold=${item.mold}
								&context=${item.context}&nowtime=${item.nowtime}"
								target="indexiframe">${item.theme}</a>-${item.nowtime}
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
	</section>

	<section class="M-type">
		<div>
			<div class="M-type-span">
				<p>
					<span>攻略类</span> <a href="AllMessage.jsp">更多</a>
				</p>
			</div>
			<div class="M-type-item">
				<ul class="M-type-ul">
					<c:forEach items="${Ms}" var="item">
						<c:if test="${item.mold=='攻略类'}">
							<li>${item.uname}-<a
								href="ViewMessage.jsp
								?num=${item.num}&uname=${item.uname}&theme=${item.theme}&mold=${item.mold}
								&context=${item.context}&nowtime=${item.nowtime}"
								target="indexiframe">${item.theme}</a>-${item.nowtime}
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
	</section>

	<section class="M-type">
		<div>
			<div class="M-type-span">
				<p>
					<span>科普类</span> <a href="AllMessage.jsp">更多</a>
				</p>
			</div>
			<div class="M-type-item">
				<ul class="M-type-ul">
					<c:forEach items="${Ms}" var="item">
						<c:if test="${item.mold=='科普类'}">
							<li>${item.uname}-<a
								href="ViewMessage.jsp
								?num=${item.num}&uname=${item.uname}&theme=${item.theme}&mold=${item.mold}
								&context=${item.context}&nowtime=${item.nowtime}"
								target="indexiframe">${item.theme}</a>-${item.nowtime}
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
	</section>

	<section class="M-type">
		<div></div>
	</section>

	<section class="M-type">
		<div></div>
	</section>
</body>
</html>