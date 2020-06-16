package com.exm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exm.model.User;
import com.exm.utils.JDBCUtils;

@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String uname = request.getParameter("uname");
		String pawd = request.getParameter("pawd");
		PrintWriter wr = response.getWriter();
		if (uname == null || uname.length() == 0) {
			wr.println("<script type='text/javascript'>" + "alert('账号不能为空！');"
					+ "location.href='Main.jsp';</script>");
			wr.close();
		} else if (pawd == null || pawd.length() == 0) {
			wr.println("<script type='text/javascript'>" + "alert('密码不能为空！');"
					+ "location.href='Main.jsp';</script>");
			wr.close();
		} /*else { // 登录失败
			wr.println("<script type='text/javascript'>" + "alert('账号或密码错误！');"
					+ "location.href='Main.jsp';</script>");
			wr.close();
		}*/
		
		String oper = getMethodStr(request);
		String result = "ErrorLogin.jsp";
		if (oper.equals("login")) {
			User user = getRequestParameter(request);
			user = login(user);
			if (user != null) {
				request.getSession().setAttribute("user", user);
				result = "Manager.jsp";
			}
		} else if (oper.equals("out")) {
			//User user = getRequestParameter(request);
			// user = out(user);
		}
		response.sendRedirect(result);
		/*
		 * PrintWriter wr = response.getWriter(); // 获取文本框uname和密码框pawd的值 String
		 * uname = request.getParameter("uname"); String pawd =
		 * request.getParameter("pawd"); // 判断逻辑 if ("admin".equals(uname) &&
		 * "8888".equals(pawd)) { // 成功登陆，并将用户保存在session对象中
		 * 
		 * // 创建对象 HttpSession session = request.getSession(); // 保存在session域
		 * session.setAttribute("loginName", uname); // 跳转到主页
		 * request.getRequestDispatcher("Manager.jsp").forward(request,
		 * response); } else if (uname == null || uname.length() == 0) {
		 * wr.println("<script type='text/javascript'>" + "alert('账号不能为空！');" +
		 * "location.href='Main.jsp';</script>"); wr.close(); } else if (pawd ==
		 * null || pawd.length() == 0) {
		 * wr.println("<script type='text/javascript'>" + "alert('密码不能为空！');" +
		 * "location.href='Main.jsp';</script>"); wr.close(); } else { // 登录失败
		 * wr.println("<script type='text/javascript'>" + "alert('账号或密码错误！');" +
		 * "location.href='Main.jsp';</script>"); wr.close(); }
		 */
	}

	private User login(User user) {
		try {
			Connection con = JDBCUtils.getConnection();
			String sql = "select * from t_user where id=? and pwd=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getId());
			ps.setString(2, user.getPwd());
			ResultSet rs = ps.executeQuery();
			User u = null;
			while (rs.next()) {
				u = new User();
				u.setId(rs.getString(1));
				u.setPwd(rs.getString(2));
			}
			rs.close();
			ps.close();
			con.close();
			return u;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private User getRequestParameter(HttpServletRequest request) {
		User user = new User();
		user.setId(request.getParameter("uname"));
		user.setPwd(request.getParameter("pawd"));
		return user;
	}

	private String getMethodStr(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String[] subUri = uri.split("/");
		String oper = subUri[subUri.length - 1];
		System.out.println(oper);
		oper = (oper.split("\\."))[0];
		return oper;
	}
}
