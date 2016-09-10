package com.congbbs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.congbbs.dao.MessageDao;
import com.congbbs.dao.ReplyDao;
import com.congbbs.factory.MessageDaoFactory;
import com.congbbs.factory.ReplyDaoFactory;
import com.congbbs.javabean.Message;
import com.congbbs.javabean.PageBean;
import com.congbbs.javabean.Reply;

public class GetMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int  messageId = Integer.parseInt(request.getParameter("messageID"));
		MessageDao messageDao = MessageDaoFactory.getMessageDaoInstance();
		Message message = messageDao.fingMessageById(messageId);
		request.setAttribute("message", message);
		
		//获得对该消息的所有回复
		 int currentPage = 0;
		 String currentPageStr = request.getParameter("currentPage");
		 if(currentPageStr == null || "".equals(currentPageStr)){
			 currentPage = 1;
		 }else {
			currentPage = Integer.parseInt(currentPageStr);
		 }
		 ReplyDao replyDao = ReplyDaoFactory.getReplyDaoInstance();
		 int totalCount = replyDao.findCountByMsgId(messageId);
		 int totalPage = 0;
		 if(totalCount%5 == 0){
			totalPage = totalCount/5;
		}else{
			totalPage = totalCount/5 + 1;
		}
		PageBean pageBean = new PageBean(currentPage, 5,totalCount,totalPage);
		List<Reply> replys = replyDao.findReplyByMsgId(messageId,pageBean);
		
		request.setAttribute("replyList",replys);
		request.setAttribute("page",pageBean);
		
		ServletContext servletContext = getServletContext();
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/jsp/showMsg.jsp");
		dispatcher.forward(request, response);
	}

}
