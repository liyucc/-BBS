package com.congbbs.javabean;

import java.util.Date;

public class User {
	private int userID;       //用户账号
	private String userName;  //用户姓名
	private boolean userSex;  //用户性别
	private Date userBirth;   //出生日期
	private String userPhone; //电话号码
	private String userPlace; //住址
	private Date joinTime;    //加入时间
	private String passWord;  //密码
	public int getUserID() {  
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isUserSex() {
		return userSex;
	}
	public void setUserSex(boolean userSex) {
		this.userSex = userSex;
	}
	public Date getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserPlace() {
		return userPlace;
	}
	public void setUserPlace(String userPlace) {
		this.userPlace = userPlace;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
