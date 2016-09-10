<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  	<div id="head"><img src="${pageContext.request.contextPath}/image/bg.jpg" width="970" height="147" /></div>
  	<div id="content0">
  	<div class="htmleaf-container">
	<nav class="animenu"> 
	  <button class="animenu__toggle">
		<span class="animenu__toggle__bar"></span>
		<span class="animenu__toggle__bar"></span>
		<span class="animenu__toggle__bar"></span>
	  </button>
	  <ul class="animenu__nav">
		<li>
		  <a href="${pageContext.request.contextPath}/jsp/main.jsp">首页</a>
		</li>
		<li>
		  <a href="#">用户个人中心</a>
		  <ul class="animenu__nav__child">
			<li><a href="">个人详细信息</a></li>
			<li><a href="">发表过的消息</a></li>
			<li><a href="">参与回复的消息</a></li>
			<li><a href="">修改密码</a></li>
		  </ul>
		</li>     
		<li>
		  <a href="${pageContext.request.contextPath}/GetMessageList"> 更多消息</a>
		  <ul class="animenu__nav__child">
			<li><a href="${pageContext.request.contextPath}/jsp/search.jsp">按关键字查询消息</a></li>
			<li><a href="">最热门的消息</a></li>
			<li><a href="">Sub Item 3</a></li>
		  </ul>
		</li>
		<li>
		 <a href="${pageContext.request.contextPath}/jsp/publish.jsp"> 发布消息</a> 
		</li>
		<li>
		  <a href="${pageContext.request.contextPath}/index.jsp">返回登录</a>
		</li>                 
	  </ul>
	</nav>
</div>
</div>