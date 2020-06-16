package com.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bbs.model.Admins;
import com.bbs.model.UsersInfo;

@WebServlet("/LoginOutServlet")
public class LoginOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");	
		HttpSession session = request.getSession();
		Admins admin = (Admins) session.getAttribute("admin");
		if (admin != null) {
			session.removeAttribute("admin");
		}
		request.getRequestDispatcher("MessageListServlet").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");	
		HttpSession session = request.getSession();
		UsersInfo user = (UsersInfo) session.getAttribute("user");
		if (user != null) {
			session.removeAttribute("user");
		}
		request.getRequestDispatcher("MessageListServlet").forward(request, response);
	}
}
