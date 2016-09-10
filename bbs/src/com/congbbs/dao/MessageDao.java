package com.congbbs.dao;

import java.util.List;

import com.congbbs.javabean.Message;
import com.congbbs.javabean.PageBean;

public interface MessageDao {
	
	//添加消息
	public void addMessage(Message message);
	
	//分页查询记录数
	public List<Message> findAllMessage(PageBean pageBean);
	
	//获得总记录数
	public int findAllCount();
	
	//根据messageId查询数据
	public Message fingMessageById(int messageId);
	
	
	//根据标题啊模糊查询消息
	public List<Message> findMessageByLike(String keyWord);
	
}
