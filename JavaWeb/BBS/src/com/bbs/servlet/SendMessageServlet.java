package com.bbs.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.utils.JDBCUtils;

public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String getMethodStr(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String[] subUri = uri.split("/");
		String methodStr = subUri[subUri.length - 1];
		System.out.println(methodStr);
		methodStr = (methodStr.split("\\."))[0];
		return methodStr;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String methodStr = getMethodStr(request);
		if (methodStr.equals("message")) {
			Connection con = JDBCUtils.getConnection();
			try {
				String sqlstr = "insert into Messagess(uname,theme,mold,context,nowtime)" + "values(?,?,?,?,?)";
				String theme = request.getParameter("theme");
				String uname = request.getParameter("uname");
				String mold = request.getParameter("mold");
				String context = request.getParameter("context");
				String nowtime = request.getParameter("nowtime");
				PreparedStatement prestr = con.prepareStatement(sqlstr);
				prestr.setString(1, uname);
				prestr.setString(2, theme);
				prestr.setString(3, mold);
				prestr.setString(4, context);
				prestr.setString(5, nowtime);
				prestr.executeUpdate();
				JDBCUtils.close(null, prestr, con);
				System.out.println("发布成功！");
				request.getRequestDispatcher("MessageListServlet").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (methodStr.equals("admin")) {
			String name = request.getParameter("name");
			String pwd = request.getParameter("pwd");
			if (name.equals("admin") && pwd.equals("123")) {
				request.getSession().setAttribute("SessionName", name);
				request.getRequestDispatcher("AllMessage.jsp").forward(request, response);
			}
		}
	}

}
