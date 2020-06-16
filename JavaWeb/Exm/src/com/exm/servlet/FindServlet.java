package com.exm.servlet;

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

import com.exm.model.Book;
import com.exm.utils.JDBCUtils;

@WebServlet("/FindServlet")
public class FindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			Connection con = JDBCUtils.getConnection();
			String sql = "select * from t_book where name like ?";
			PreparedStatement ps = con.prepareStatement(sql);
			String find = (String) request.getParameter("selectname");
			ps.setString(1, "%" + find + "%");
			ResultSet rs = ps.executeQuery();
			System.out.println("查询成功！");
			@SuppressWarnings("rawtypes")
			List<Book> list = new ArrayList();
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString(1));
				book.setName(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setAvailable(rs.getString(4));
				list.add(book);
			}
			request.setAttribute("list", list);
			rs.close();
			ps.close();
			con.close();
			request.getRequestDispatcher("List.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
