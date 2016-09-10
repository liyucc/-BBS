package com.congbbs.factory;

import com.congbbs.dao.ReplyDao;
import com.congbbs.dao.imple.ReplyDaoImpl;

public class ReplyDaoFactory {
	public static ReplyDao getReplyDaoInstance(){
		return new ReplyDaoImpl();
	}
}
