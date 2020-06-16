package com.bbs.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bbs.model.Review;
import com.bbs.utils.JDBCUtils;

@WebServlet("/Rev")
public class Rev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session=request.getSession();
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
	}
}
