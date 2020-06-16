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
@WebServlet({ "/DeleteServlet" })
public class DeleteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		try {
			Connection con = JDBCUtils.getConnection();
			System.out.println("删除");
			String sql = "delete from t_book where isbn=?";
			PreparedStatement ps = con.prepareStatement(sql);
			String isbn = req.getParameter("isbn");
			ps.setString(1, isbn);
			ps.execute();
			ps.close();
			con.close();
			req.getRequestDispatcher("ListServlet").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
