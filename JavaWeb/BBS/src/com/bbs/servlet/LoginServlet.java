package com.bbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.model.Admins;
import com.bbs.model.AdminsDao;
import com.bbs.model.UserDao;
import com.bbs.model.UsersInfo;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		PrintWriter writer = response.getWriter();
		AdminsDao admindado = new AdminsDao();
		Admins admin = admindado.login(id, pwd);
		if (admin != null) {
			request.getSession().setAttribute("admin", admin);
			request.getRequestDispatcher("MessageListServlet").forward(request, response);
		} else {
			writer.println("<script type='text/javascript'>" + "alert('密码不正确！');" + "location.href='Login.jsp';</script>");
			writer.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String account = request.getParameter("account");
		String upassword = request.getParameter("upassword");

		PrintWriter writer = response.getWriter();

		UserDao userdao = new UserDao();
		UsersInfo user = userdao.login(account, upassword);
		if (user != null) {
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("MessageListServlet").forward(request, response);
		} else {
			writer.println("<script type='text/javascript'>" + "alert('密码不正确！');" + "location.href='Login.jsp';</script>");
			writer.close();
		}
	}

}
