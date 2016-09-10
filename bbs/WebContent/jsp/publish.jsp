<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>发布消息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/my.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/foot.css">
<style type="text/css">
body {
	background-Color: #CCC;
}
</style>
</head>

<body>
	<jsp:include page="top.jsp" flush="true"></jsp:include>
	<div id="content1">
		<div id="place">
			当前位置：[<a href="index.jsp">首页</a>] - [发布消息]
		</div>
		<div>
			<p>
				<font color="red">${requestScope.error}</font>
			</p>
			<form action="/bbs/Publish" method="post">
				<p>
					消息标题： <input type="text" name="title" size="156"/>
				</p>
				<p>
					消息内容：
    				<FCK:editor instanceName="content" basePath="/fckeditor" toolbarSet="myToolbar" height="300" width="970"></FCK:editor>
				</P>
				<p align="center">
					<input type="submit" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="reset" value="重置" />
				</p>
			</form>
		</div>
	</div>
	<jsp:include page="foot.jsp" flush="true"></jsp:include>
</body>
</html>
