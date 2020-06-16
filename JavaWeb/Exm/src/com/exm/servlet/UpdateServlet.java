package com.exm.servlet;

import com.exm.utils.JDBCUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet({ "/UpdateServlet" })
public class UpdateServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String isbn = request.getParameter("isbn");
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String available = request.getParameter("available");
		String sql = " update t_book set isbn= ?,name= ?,author= ?,available= ?  where isbn= ? ";
		try {
			Connection con = JDBCUtils.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, isbn);
			ps.setString(2, name);
			ps.setString(3, author);
			ps.setString(4, available);
			ps.setString(5, isbn);
			ps.executeUpdate();
			ps.close();
			con.close();
			request.getRequestDispatcher("ListServlet").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
