package com.congbbs.dao;

import com.congbbs.javabean.Message;
import com.congbbs.javabean.User;

public interface UserDao {
	public User findUserById(int userId);
	
	public String modfiyPassword(User user);
}
