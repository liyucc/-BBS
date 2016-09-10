package com.congbbs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DbConn {
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost:3306/db_bbsmanage";
	private static final String USERNAME="root";
	private static final String PASSWORD="168998";
	
	//定义获得数据库链接的方法
	public static Connection getConnection(){
		Connection conn = null;
		try{
			//注册驱动
			Class.forName(DRIVER);
			//获取Connection链接
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭conn链接的方法
	public static void close(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//关闭预处理对象PerparedStatement的方法
	public static void close(PreparedStatement ps){
		if(ps != null){
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//关闭结果集对象ResultSet的方法
	public static void close(ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
