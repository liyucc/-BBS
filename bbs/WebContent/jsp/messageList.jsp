<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>消息列表</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/my.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/foot.css">
    <style type="text/css">
   .STYLE2 {color: red;
	font-size: 14px;
    }
    body{
    width: 970px;
    margin: 0 auto;
    background-Color:#CCC;
    }
    dl dl:hover {
    background:blue;
    }
    dl a{
    text-decoration:none;
    }
    </style>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/nav.js"></script>
  </head>
  
  <body>
	<jsp:include page="top.jsp" flush="true"></jsp:include>
  	<div id="place">当前位置：[<a href="index.jsp">首页</a>] - [消息列表]</div>
   	<div id="content2">
   	<div id="channelcont">
	<div id="channelleft">
	<div class="channelinleft">
	<div class="channeltit"></div>
	<dl>
	<c:forEach items="${requestScope.messages}" var="message">
	<dl>
	<dt>>><a href="${pageContext.request.contextPath}/GetMessage?messageID=${message.messageID}">${message.messageTitle}</a></dt>
	
	  <div align="right">发布人ID：${message.userID} 
	  		发布时间：${message.publishTime}</div>
	<hr/>
	</dl>
	</c:forEach>
	</dl>
 	<div align="center">
 	<c:if test="${requestScope.pageBean.currPage != 1}">	
		<a href="GetMessageList?currPage=1">首页</a> | 
		<a href="GetMessageList?currPage=${requestScope.pageBean.currPage-1}">上一页</a>
	</c:if>	
	<c:if test="${requestScope.pageBean.currPage!=requestScope.pageBean.tatalPage}">			
		<a href="GetMessageList?currPage=${requestScope.pageBean.currPage+1}">下一页</a> | 
		<a href="GetMessageList?currPage=${requestScope.pageBean.tatalPage}">尾页</a>
	</c:if>	
	当前为第${requestScope.pageBean.currPage}页,共${requestScope.pageBean.tatalPage}页
  </div>
</div>
</div>
</div>
</div>
<jsp:include page="foot.jsp" flush="true"></jsp:include>
</body>
</html>
