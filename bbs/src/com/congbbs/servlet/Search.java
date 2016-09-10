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
import com.congbbs.factory.MessageDaoFactory;
import com.congbbs.javabean.Message;

public class Search extends HttpServlet {
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
		String keyWord = request.getParameter("keyword");
		if("".equals(keyWord)){
			keyWord=null;
		}
		MessageDao messageDao = MessageDaoFactory.getMessageDaoInstance();
		List<Message> msgs = messageDao.findMessageByLike(keyWord);
		request.setAttribute("msgs", msgs);
		ServletContext servletContext = getServletContext();
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/jsp/search.jsp");
		dispatcher.forward(request, response);
	}

}
