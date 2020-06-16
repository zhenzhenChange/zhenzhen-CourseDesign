<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.bbs.model.*" import="java.io.IOException" import="java.sql.*" import="java.util.*" import="com.bbs.utils.JDBCUtils"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AllDataList</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int i = 0;
		try {
			Connection con = JDBCUtils.getConnection();
			String sql = "select * from Messagess";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Messagess> list = new ArrayList<Messagess>();
			while (rs.next()) {
				Messagess Mes = new Messagess();
				Mes.setNum(rs.getString("num"));
				Mes.setUname(rs.getString("uname"));
				Mes.setTheme(rs.getString("theme"));
				Mes.setMold(rs.getString("mold"));
				Mes.setContext(rs.getString("context"));
				Mes.setNowtime(rs.getString("nowtime"));
				list.add(Mes);
				i++;
			}
			session.setAttribute("li", i);
			request.setAttribute("Ms", list);
			JDBCUtils.close(rs, ps, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("AllMessage.jsp").forward(request, response);
	%>
</body>
</html>