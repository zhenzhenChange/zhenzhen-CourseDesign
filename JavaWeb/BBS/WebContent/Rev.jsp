<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bbs.model.*" import="java.io.IOException" import="java.sql.*" import="java.util.*" import="com.bbs.utils.JDBCUtils"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int n = 0;
		try {
			Connection con = JDBCUtils.getConnection();
			String sql = "select * from Review";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Review> list = new ArrayList<Review>();
			while (rs.next()) {
				Review rev = new Review();
				rev.setRnum(rs.getString("rnum"));
				rev.setUname(rs.getString("uname"));
				rev.setTheme(rs.getString("theme"));
				rev.setRevtext(rs.getString("revtext"));
				rev.setRetime(rs.getString("retime"));
				list.add(rev);
				n++;
			}
			session.setAttribute("li", n);
			request.setAttribute("rev", list);
			JDBCUtils.close(rs, ps, con);
			System.out.println("查询评论！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("Review.jsp").forward(request, response);
	%>
</body>
</html>