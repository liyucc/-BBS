package com.congbbs.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.congbbs.dao.MessageDao;
import com.congbbs.factory.MessageDaoFactory;
import com.congbbs.javabean.Message;
import com.congbbs.javabean.User;

public class Publish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");     //设置request请求的编码
		response.setCharacterEncoding("UTF-8");    //设置response相应的编码
		response.setContentType("text/html; charset=UTF-8");   //告知浏览器该页面的解析格式和编码
		String title = request.getParameter("title");    //接收表单提交的数据,这里的"title"要了表单中的name属性一致
		String content = request.getParameter("content"); //同理
		ServletContext servletContext = getServletContext();   //获得servlet内置的上下文对象
		RequestDispatcher dispatcher = null;    //定义分发对象
		User user = (User)request.getSession().getAttribute("user");  //获取登录时提交在session中的用户对象数据
		//进行简单的验证，将信息反馈回页面
		if(user == null){
			request.setAttribute("error", "要发布消息必须先登录！！");
			dispatcher = servletContext.getRequestDispatcher("/jsp/publish.jsp");  //转发回原界面
		}else {
			if(title == null || "".equals(title)){
				request.setAttribute("error", "必须要输入消息标题！！");
				dispatcher = servletContext.getRequestDispatcher("/jsp/publish.jsp");
			}else{
				if(content == null || "".equals(content)){
					request.setAttribute("error", "不能发布空消息！！！");
					dispatcher = servletContext.getRequestDispatcher("/jsp/publish.jsp");
				}else {
					Message message = new Message();      //通过数据验证后，将数据装配到一个message对象中传给后台
					message.setUserID(user.getUserID());
					message.setMessageTitle(title);
					message.setMessageContent(content);
					message.setPublishTime(new Date());
					
					MessageDao messageDao = MessageDaoFactory.getMessageDaoInstance();  //获得message业务处理的实例
					messageDao.addMessage(message);   //调用插入方法
					dispatcher = servletContext.getRequestDispatcher("/GetMessageList");  //插入成功转发到显示消息的servlet进行查询显示
				}
			}
		}
		dispatcher.forward(request, response);  //跳转页面
	}

}
