package com.congbbs.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.congbbs.dao.UserDao;
import com.congbbs.javabean.User;
import com.congbbs.util.DbConn;

public class UserDaoImpl implements UserDao {

	@Override
	public User findUserById(int userId) {
		Connection conn = DbConn.getConnection(); //获得数据库连接
		PreparedStatement ps = null;
		ResultSet rs = null; 
		User user = new User(); //实例化一个User对象来装载查询结果
		String sql = "select * from tb_user where userId = ?"; //编写sql查询语句
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if(rs.next()){   //将查询到的结果装入user中
				user.setUserID(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setUserSex(rs.getBoolean(3));
				user.setUserBirth(rs.getDate(4));
				user.setUserPhone(rs.getString(5));
				user.setUserPlace(rs.getString(6));
				user.setJoinTime(rs.getDate(7));
				user.setPassWord(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {            //关闭连接对象，有内层到外层关闭
			DbConn.close(rs);
			DbConn.close(ps);
			DbConn.close(conn);
		}
		return user;  //返回user
	}

	@Override
	public String modfiyPassword(User user){
		Connection conn = DbConn.getConnection();
		PreparedStatement ps= null;
		String sql="update tb_user set userName=?, userSex=?,userBirth=?,userPhone=?,"
		+"userPlace=?,joinTime=?,password=? where userID = ?";
		try { 
			ps = conn.prepareStatement(sql);
			ps.setString(1,user.getUserName());
			ps.setBoolean(2,user.isUserSex());
			ps.setDate(3,new java.sql.Date(user.getUserBirth().getTime()));
			ps.setString(4, user.getUserPhone());
			ps.setString(5, user.getUserPlace());
			ps.setDate(6, new java.sql.Date(user.getJoinTime().getTime()));
			ps.setString(7, user.getPassWord());
			ps.setInt(8, user.getUserID());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbConn.close(ps);
			DbConn.close(conn);
		}
		return "修改成功";
	}
	
	
	
}
