package com.congbbs.factory;

import com.congbbs.dao.UserDao;
import com.congbbs.dao.imple.UserDaoImpl;

public class UserDaoFactory { //工厂类，用于获得相对应的工厂方法
	public static UserDao getUserDaoInstance(){ //获得实例对象的方法
		return new UserDaoImpl();
	}
}
