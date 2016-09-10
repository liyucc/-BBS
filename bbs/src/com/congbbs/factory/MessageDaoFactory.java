package com.congbbs.factory;

import com.congbbs.dao.MessageDao;
import com.congbbs.dao.imple.MessageDaoImpl;

public class MessageDaoFactory {
	public static MessageDao getMessageDaoInstance(){
		return new MessageDaoImpl();
	}
}
