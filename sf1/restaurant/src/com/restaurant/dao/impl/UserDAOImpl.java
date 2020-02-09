package com.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.restaurant.dao.BaseDAO;
import com.restaurant.dao.UserDAO;
import com.restaurant.entity.Users;

public class UserDAOImpl extends BaseDAO implements UserDAO {
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	@Override
	public int addUsers(Users user) {
		int result=0;
		String sql="insert into users(loginName,loginPwd,trueName,email,phone,address,status) values(?,?,?,?,?,?,?)";
		try {
			conn=this.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getLoginName());
			pstmt.setString(2, user.getLoginPwd());
			pstmt.setString(3, user.getTrueName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getAddress());
			pstmt.setInt(7, 1);
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		return result;
	}

}
