package com.bbs.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bbs.model.UsersInfo;
import com.bbs.utils.JDBCUtils;

public class UserDao {
	public UsersInfo login(String account, String upassword) {
		UsersInfo user = null;
		Connection con = JDBCUtils.getConnection();
		String sql = "select * from UsersInfo where account = ? and upassword = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, upassword);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new UsersInfo();
				user.setAccount(rs.getString("account"));
				user.setUpassword(rs.getString("upassword"));
			}
			JDBCUtils.close(rs, ps, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean finduser(String account) {
		Connection con = JDBCUtils.getConnection();
		String sql = "select * from UsersInfo where account = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, account);
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
