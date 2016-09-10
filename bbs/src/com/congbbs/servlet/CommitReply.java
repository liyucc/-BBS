package com.congbbs.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.congbbs.dao.ReplyDao;
import com.congbbs.factory.ReplyDaoFactory;
import com.congbbs.javabean.Reply;
import com.congbbs.javabean.User;

public class CommitReply extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String replyContent = request.getParameter("replyContent");
		int messageId =Integer.parseInt(request.getParameter("messageID"));
		ServletContext servletContext = getServletContext();
		RequestDispatcher dispatcher = null;
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(user == null){
			request.setAttribute("error", "要进行回复必须首先登录！！");
		}else {
			if(replyContent == null || "".equals(replyContent)){
				request.setAttribute("error", "必须输入内容");
			}else {
				Reply reply = new Reply();
				reply.setReplyContent(replyContent);
				reply.setMessageID(messageId);
				reply.setUserID(user.getUserID());
				reply.setReplyTime(new Date());
				ReplyDao replyDao = ReplyDaoFactory.getReplyDaoInstance();
				replyDao.addreply(reply);
			}
		}
		dispatcher = servletContext.getRequestDispatcher("/GetMessage?messageID="+messageId);
		dispatcher.forward(request, response);
	}

}
