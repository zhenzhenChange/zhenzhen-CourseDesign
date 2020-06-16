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

import com.bbs.utils.JDBCUtils;
import com.bbs.model.Messagess;

@WebServlet("/MessageListServlet")
public class MessageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MessageListServlet() {
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
		try {
			Connection con = JDBCUtils.getConnection();
			String sql = "select * from Messagess";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Messagess> list = new ArrayList<Messagess>();
			while (rs.next()) {
				Messagess Mes = new Messagess();
				Mes.setNum(rs.getString("num"));
				Mes.setUname(rs.getString("uname"));
				Mes.setTheme(rs.getString("theme"));
				Mes.setMold(rs.getString("mold"));
				Mes.setContext(rs.getString("context"));
				Mes.setNowtime(rs.getString("nowtime"));
				list.add(Mes);
			}
			request.setAttribute("Ms", list);
			JDBCUtils.close(rs, ps, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("MessageBoard.jsp").forward(request, response);
	}

}
