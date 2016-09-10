<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>显示信息</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/my.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/foot.css">
    <style type="text/css">
	.STYLE1 {font-size: 16px} 
	.STYLE2 {
		color: red;
		font-size: 14px;
	}
	.STYLE3 {font-size: 14px}
	.STYLE1 ul li{
	height:60px;
	width: 600px;
	list-style:none;
	text-align:left;
	}
	.STYLE1 ul li p a{
	height:60px;
	text-decoration:none;
	}
	.STYLE1 ul li p a:hover{
	background:#CCC;
	}
	</style>
  </head>
  
  <body>
  <jsp:include page="top.jsp" flush="true"></jsp:include>
  <div id="content3">
  <div id="thenew">
  <div class="tit">
  <h1>最新消息</h1>
  </div>
<div class="STYLE1" id="therecom">
	<ul>
	<c:forEach var="messages" items="${list}">
	<li><p><a href="${pageContext.request.contextPath}/GetMessage?messageID=${messages.messageID}"><c:out value="${messages.messageTitle}"/></a>
	<span class="STYLE2"><c:out value="${messages.publishTime}"/></span></p></li>
	</c:forEach>
	</ul>
</div>
</div>
<div id="menunav">
<div class="tit">
  <h1>用户信息</h1>
</div>
<div id="employee">
	<c:choose>
		<c:when test="${empty sessionScope.user}">
			没有进行身份识别，请先进行身份识别! 
		</c:when>
		<c:otherwise>
			<ul>
			  <li>用户姓名：${user.userName}</li>
			  <li>用户性别：${user.userSex ? "男":"女"}</li>
			  <li>出生日期：${user.userBirth }</li>
			  <li>住址：${user.userPlace }</li>
			</ul>
		</c:otherwise>
	</c:choose>
	</div>
	</div>
	</div>
	<jsp:include page="foot.jsp" flush="true"></jsp:include>
  </body>
</html>
