package com.exm.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static String URL = "jdbc:sqlserver://localhost:1433;databaseName=Exmp;";

	private static String USER_NAME = "sa";

	private static String PASS_WORD = "123456";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER_NAME, PASS_WORD);
			System.out.println("数据库连接成功！");
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
