<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/my.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/foot.css">
<style type="text/css">
.keyword{
background:#EEE;
 width:500px;
 margin: 0px auto;
}
.messagelist{
width: 970px;
margin: 0px auto;
}
dl dl:hover {
  background:blue;
}
dl a{
  text-decoration:none;
}
</style>
</head>
<body>
<jsp:include page="top.jsp" flush="true"></jsp:include>
<div class="keyword">
<form action="${pageContext.request.contextPath}/Search" method="post">
请输入查询的关键字<input type="text" name="keyword"><input type="submit" value="搜索一下">
</form>
</div>
<div class="messagelist">
<c:choose>
<c:when test="${ empty requestScope.msgs}">
没有搜索到相关的信息！！！！！
</c:when>
<c:otherwise> 
<dl>
	<c:forEach items="${requestScope.msgs}" var="message">
	<dl>
	<dt>>><a href="${pageContext.request.contextPath}/GetMessage?messageID=${message.messageID}">${message.messageTitle}</a></dt>
	<div align="right">发布人ID：${message.userID} 
	  发布时间：${message.publishTime}</div>
	<hr/>
	</dl>
	</c:forEach>
</dl>
</c:otherwise>
</c:choose>
</div>
<jsp:include page="foot.jsp" flush="true"></jsp:include>
</body>
</html>