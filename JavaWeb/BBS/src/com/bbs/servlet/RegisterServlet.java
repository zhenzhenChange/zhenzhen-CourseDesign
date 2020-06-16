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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String account = request.getParameter("account");
		String upassword = request.getParameter("upassword");
		String uname = request.getParameter("uname");
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth");
		String hobbies = request.getParameter("hobbies");
		String resident = request.getParameter("resident");
		String degree = request.getParameter("degree");
		String position = request.getParameter("position");
		String tele = request.getParameter("tele");
		String email = request.getParameter("email");
		try {
			Connection con = JDBCUtils.getConnection();
			String sql = "insert into UsersInfo(account,upassword,uname,gender,birth,hobbies,resident,degree,position,tele,email) values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, upassword);
			ps.setString(3, uname);
			ps.setString(4, gender);
			ps.setString(5, birth);
			ps.setString(6, hobbies);
			ps.setString(7, resident);
			ps.setString(8, degree);
			ps.setString(9, position);
			ps.setString(10, tele);
			ps.setString(11, email);
			ps.executeUpdate();
			JDBCUtils.close(null, ps, con);
			request.getRequestDispatcher("MessageListServlet").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
