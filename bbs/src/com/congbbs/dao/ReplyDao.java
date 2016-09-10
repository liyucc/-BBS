package com.congbbs.dao;

import java.util.List;

import com.congbbs.javabean.PageBean;
import com.congbbs.javabean.Reply;


public interface ReplyDao {
	
	public void addreply(Reply reply);
	
	public List<Reply> findReplyByMsgId(int messageId, PageBean pageBean);
	
	public int findCountByMsgId(int messageID);
	
}
