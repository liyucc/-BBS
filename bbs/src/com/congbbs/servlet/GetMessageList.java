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
import com.congbbs.javabean.PageBean;

public class GetMessageList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int currentPage = 0; //定义当前页
		String currentPageStr = request.getParameter("currPage"); //从界面获取当前页
		if(currentPageStr == null || "".equals(currentPageStr)){  //没有传值当前页则设置为默认的第一页
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(currentPageStr);  //将获得的当前页字串转为int
		}
		MessageDao messageDao = MessageDaoFactory.getMessageDaoInstance();  //获得message业务逻辑实例
		int totalCount = messageDao.findAllCount();  //查询总记录数
		int totalPage = 0;
		int pageSize = 5;  //定义每页显示的记录数
		if(totalCount%pageSize == 0){   //总页数的计算方法整除无余数
			totalPage = totalCount/pageSize; //直接设为总页数
		}else{ 						//无法整除页数加一
			totalPage = totalCount/pageSize + 1;
		}
		PageBean pageBean = new PageBean(currentPage, pageSize,totalCount, totalPage);
		List<Message> messages = messageDao.findAllMessage(pageBean);
		request.setAttribute("messages", messages);  //将查询的数据传到request域中
		request.setAttribute("pageBean", pageBean);  //将分页信息传到request域中
		ServletContext servletContext = getServletContext(); 
		RequestDispatcher dispatcher = servletContext
				.getRequestDispatcher("/jsp/messageList.jsp");
		dispatcher.forward(request, response);
	}

}
