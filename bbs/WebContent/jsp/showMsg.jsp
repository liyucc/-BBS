<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>显示详细信息</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/my.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/foot.css">
	<style type="text/css">
   <!--
   .STYLE1{
   background-color:#6495ED;
   }
   .STYLE2 {
	font-size: 15px;
    }
    -->
    body{
    width: 970px;
    margin: 0 auto;
    background-Color:#CCC;
    }
    </style>
  </head>
  <body>
  <div id="content4">
  <jsp:include page="top.jsp" flush="true"></jsp:include>
  <div id="place">当前位置：[<a href="index.jsp">首页</a>] - [详细消息]</div>
    <div id="channelcont">
    <div id="channelleft">
    <div class="channelinleft">
	<br/>
	<h2 align="center">${message.messageTitle}</h2>
	<br/>
	${message.messageContent}
	<div class="STYLE2" align="right">发布人ID：${message.userID} 
	  		发布时间：${message.publishTime}</div>
	<hr/>
	<!--回复-->
	<c:forEach items="${requestScope.replyList}" var="reply">
	<div class="STYLE1">
		${reply.replyContent}
		<div class="STYLE2" align="right">
		      回复人ID：${reply.userID} 
	  	      回复时间：${reply.replyTime}</div>
	  	<hr/>
	</div>
	</c:forEach> 
	<div align="center">
		第<c:forEach varStatus="stat" begin="1" end="${page.tatalPage}">
			<a href="GetMessage?messageID=${message.messageID}&currentPage=${stat.index}">${stat.index}</a>
		</c:forEach>页
	</div>
	<div>
	  <div align="left">
	  	<p>回复:</p>
	  	<p><font color="red">${error}</font></p>
	  	<form action="CommitReply" method="post">
	    <p>
	    <FCK:editor instanceName="replyContent" basePath="/fckeditor" toolbarSet="myToolbar" height="400" width="970"></FCK:editor>
	  	</p>	
	  		<input type="hidden" name="messageID" value="${message.messageID}"/> 
	  		<input type="submit" value="提交"/>
	  		<input type="reset" value="重置"/>	
	  	</form>	 
	  </div>
	</div>
	</div>
	</div>
	</div>
	</div>
	<jsp:include page="foot.jsp" flush="true"></jsp:include>
</body>
</html>
