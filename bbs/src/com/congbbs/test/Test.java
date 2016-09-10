package com.congbbs.test;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.congbbs.dao.MessageDao;
import com.congbbs.dao.ReplyDao;
import com.congbbs.dao.UserDao;
import com.congbbs.factory.MessageDaoFactory;
import com.congbbs.factory.ReplyDaoFactory;
import com.congbbs.factory.UserDaoFactory;
import com.congbbs.javabean.Message;
import com.congbbs.javabean.PageBean;
import com.congbbs.javabean.Reply;
import com.congbbs.javabean.User;
import com.congbbs.util.DbConn;

public class Test {
	
	@org.junit.Test
	public void testConnection(){
		Connection connection = DbConn.getConnection();
		System.out.println("数据库已经连接成功。。。");
	}
	@org.junit.Test
	public void testReply(){
		ReplyDao replyDao = ReplyDaoFactory.getReplyDaoInstance();
		PageBean pageBean = new PageBean(0, 5, replyDao.findCountByMsgId(1), replyDao.findCountByMsgId(14)/5);
		List<Reply> list =replyDao.findReplyByMsgId(1, pageBean);
		System.out.println("............");
		
		for (Reply reply : list) {
			System.out.println(reply.getReplyID());
		}
		
	}
	@org.junit.Test
	public void testMessage(){
		MessageDao messageDao = MessageDaoFactory.getMessageDaoInstance();
		int i = messageDao.findAllCount();
		System.out.println("总记录数"+i);
		PageBean pageBean = new PageBean(1, 5, i, i/5);
		List<Message> list=messageDao.findAllMessage(pageBean);
		for (Message message : list) {
			System.out.println(message.getMessageTitle());
		}
	}
	
	@org.junit.Test
	public void testAddMessage(){
		MessageDao messageDao = MessageDaoFactory.getMessageDaoInstance();
		Message message = new Message();
		message.setMessageTitle("测试信息");
		message.setMessageContent("插入成功");
		message.setUserID(Integer.parseInt("10086"));
		message.setPublishTime(new Date());
		
		messageDao.addMessage(message);
		System.out.println("插入成功。。。。。。。。。");
	}
	
	@org.junit.Test
	public void testLikeMessage(){
		MessageDao messageDao = MessageDaoFactory.getMessageDaoInstance();
		String keyword =null;
		List<Message> list = messageDao.findMessageByLike(keyword);
		for (Message message : list) {
			System.out.println(message.getMessageTitle());
		}
	}
	@org.junit.Test
	public void testUser(){
		UserDao userDao = UserDaoFactory.getUserDaoInstance();
		User user = new User();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = "1998-01-08";
		String string = "2016-08-08";
		try {
			  Date date = sdf.parse(str);
			  Date date2 = sdf.parse(string);
			  user.setJoinTime(date);
			  user.setUserBirth(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setPassWord("145678");
		user.setUserID(9526);
		user.setUserName("周六");
		user.setUserPhone("10123457854");
		user.setUserPlace("港城");
		user.setUserSex(false);
		
		String s=userDao.modfiyPassword(user);
		
		System.out.println(s);
	}
}
