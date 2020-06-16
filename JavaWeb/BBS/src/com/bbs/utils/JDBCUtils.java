package com.bbs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtils {
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static String URL = "jdbc:sqlserver://localhost:1433;databaseName=JavaWebBBS;";

	private static String USER_NAME = "sa";

	private static String PASS_WORD = "123456";

	// 静态代码块，驱动只需执行一次
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 数据库连接方法getConnection()
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER_NAME, PASS_WORD);
			System.out.println("数据库连接成功！");
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	// 关闭连接close（ResultSet rs,PreparedStatement ps，Connection con）
	public static void close(ResultSet rs, PreparedStatement ps, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ps = null;
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				con = null;
			}
		}
	}
}
