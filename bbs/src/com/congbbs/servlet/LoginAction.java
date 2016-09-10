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
import com.congbbs.dao.UserDao;
import com.congbbs.factory.MessageDaoFactory;
import com.congbbs.factory.UserDaoFactory;
import com.congbbs.javabean.Message;
import com.congbbs.javabean.PageBean;
import com.congbbs.javabean.User;


public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ServletContext servletContext =  getServletContext(); //获得上下文ServletContext对象
		RequestDispatcher dispatcher = null; //定义一个页面跳转对象
		//接收从表单提交过来的数据
		String userid=request.getParameter("userID");
		String password=request.getParameter("password");
		String checkcode=request.getParameter("validateCode");
		//从session中获取绘制验证码时保存在其中的验证码信息
		String servletCheckCode = (String)request.getSession().getAttribute("chenkcode");
		//调用findUserById方法查询
		UserDao userDao = UserDaoFactory.getUserDaoInstance(); //获得实例对象
		User user = userDao.findUserById(Integer.parseInt(userid));
		MessageDao messageDao = MessageDaoFactory.getMessageDaoInstance();
		PageBean pageBean = new PageBean(1, 5, messageDao.findAllCount(), messageDao.findAllCount()/5);
		List<Message> list = messageDao.findAllMessage(pageBean);
		request.getSession().setAttribute("list", list);// 传递到其他的页面中去方便使用
		//简单判断用户账号的输入
		if (userid == null || "".equals(userid)) { // 判断是否输入用户编号
			request.setAttribute("error", "请输入用户编号!");
			dispatcher = servletContext.getRequestDispatcher("/index.jsp");// 设置跳转页面
		} else {
			if (password == null || "".equals(password)) { // 判断是否输入系统密码
				request.setAttribute("error", "请输入密码!");
				dispatcher = servletContext.getRequestDispatcher("/index.jsp");// 设置跳转页面
			} else {
				if (checkcode == null || "".equals(checkcode)) { // 判断是否输入验证码
					request.setAttribute("error", "请输入验证码!");
					dispatcher = servletContext
							.getRequestDispatcher("/index.jsp");// 设置跳转页面
				} else {
					if (!checkcode.equals(servletCheckCode)) {
						request.setAttribute("error", "验证码错误");
						dispatcher = servletContext
								.getRequestDispatcher("/index.jsp");
					} else {
						if (user == null) {
							request.setAttribute("error", "该用户编号不存在!");
							dispatcher = servletContext
									.getRequestDispatcher("/index.jsp");
						} else {
							if (password.equals(user.getPassWord())) {
								request.getSession().setAttribute("user", user); // 将用户对象信息保存到session范围
								dispatcher = servletContext
										.getRequestDispatcher("/jsp/main.jsp");
							} else {
								request.setAttribute("error", "密码不正确!");
								dispatcher = servletContext
										.getRequestDispatcher("/index.jsp");
							}
						}
					}
				}
			}
		}
		dispatcher.forward(request, response);
	}
}
