package com.bbs.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.utils.JDBCUtils;

@WebServlet("/ViewMessageServlet")
public class ViewMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String uname=request.getParameter("uname");
		String theme=request.getParameter("theme");
		String revtext=request.getParameter("revtext");
		String retime=request.getParameter("retime");
		try {
			Connection con = JDBCUtils.getConnection();
			String sql="insert into Review(uname,theme,revtext,retime)" + "values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, theme);
			ps.setString(3, revtext);
			ps.setString(4, retime);
			ps.executeUpdate();
			JDBCUtils.close(null, ps, con);
			System.out.println("评论成功！");
			request.getRequestDispatcher("Review.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
