<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>桂航星海BBS</title>
<link href="BBS.css" rel="stylesheet" type="text/css" />
</head>
<body id="indexbody">
	<div id="page-div">
		<nav id="top-nav">
			<form action="Register.jsp" target="indexiframe">
				<div>
					<input type="submit" class="index-btn" value="注册">
				</div>
			</form>
			<form action="Login.jsp" target="indexiframe">
				<div>
					<input type="submit" class="index-btn" value="登录">
				</div>
			</form>
			<form action="Admin.jsp" target="indexiframe">
				<div>
					<input type="submit" class="index-btn" value="管理员登录">
				</div>
			</form>

			<div id="top-menu">
				<ul id="top-menu-ul">
					<li class="list">
						<form action="DataList.jsp" target="indexiframe">
							<div>
								<input type="submit" class="" value="首页">
							</div>
						</form>
					</li>
					<li class="list">
						<form action="Rev" target="indexiframe">
							<div>
								<input type="submit" class="" value="评论">
							</div>
						</form>
					</li>
					<li class="list">
						<form action="AllDataList.jsp" target="indexiframe">
							<div>
								<input type="submit" class="" value="管理贴">
							</div>
						</form>
					</li>
					<li class="list">
						<form action="SendMessage.jsp" target="indexiframe">
							<div>
								<input type="submit" class="" value="发贴">
							</div>
						</form>
					</li>
				</ul>
			</div>
		</nav>
		<!-- 顶部 -->

		<nav id="left-nav">
			<div id="returnback">
				<input class="returnbtn" type="button" onclick="javascript:history.back(-1);" value="返回">
			</div>
			<font color="red"> 失物招领</font>
		</nav>
		<!-- 左部 -->

		<div id="page-content">
			<iframe name="indexiframe" id="index-iframe" src="DataList.jsp"	style="frameborder: no; border: 0;"></iframe>
		</div>
		<!-- 内容页 -->

		<nav id="bottom-nav">
			<font color="black" size="3" face="楷体">
				@20170700304---[12-29-33-32] <br> @17软件工程4班-[李文洁-钟祯-江素媚-莫春芬]
			</font>
		</nav>
		<!-- 底部 -->
	</div>
</body>
</html>