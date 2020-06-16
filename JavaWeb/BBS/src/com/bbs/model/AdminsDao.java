package com.bbs.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bbs.utils.JDBCUtils;

public class AdminsDao {
	public Admins login(String id, String pwd) {
		Admins admin = null;
		Connection con = JDBCUtils.getConnection();
		String sql = "select * from Admins where id = ? and pwd = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				admin = new Admins();
				admin.setId(rs.getString("id"));
				admin.setPwd(rs.getString("pwd"));
			}
			JDBCUtils.close(rs, ps, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}

	public boolean finduser(String id) {
		Connection con = JDBCUtils.getConnection();
		String sql = "select * from Admins where id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				return true;
			}
			JDBCUtils.close(rs, ps, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
