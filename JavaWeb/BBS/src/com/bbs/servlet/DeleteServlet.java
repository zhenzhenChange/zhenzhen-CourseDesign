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

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			Connection con = JDBCUtils.getConnection();
			String sql = "delete from Messagess where num=?";
			PreparedStatement ps = con.prepareStatement(sql);
			String num = request.getParameter("num");
			ps.setString(1, num);
			ps.execute();
			JDBCUtils.close(null, ps, con);
			System.out.println("删除帖子！");
			request.getRequestDispatcher("AllDataList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
