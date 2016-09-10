<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>BBS-首页</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/my.css">
<script type="text/javascript">
	function changImg(){
		document.getElementById("validateCodeImg").src="${pageContext.request.contextPath}/DrawImage?"+Math.random();
	}
</script>
</head>

<body>
	<div id="content5">
		<div id="shenfenshibie">
			<form action="LoginAction" method="post">
				<font color="red">${requestScope.error}</font>
				<p>
					用户账号：<input type="text" name="userID"/>
				</p>
				<br/>
				<p>
					系统口令：<input type="password" name="password" />
				</p>
				<br/>
				<p>
					验   证   码：<input type="text" name="validateCode" /> 
				</p>
				<p>&nbsp;</p>
				<img alt="验证码看不清，换一张"
					src="${pageContext.request.contextPath}/DrawImage?"
					id="validateCodeImg" onclick="changImg()"> <a
					href="javascript:void(0)" onclick="changImg()">看不清，换一张</a> <br />
				<p align="center">
					<input type="submit" value="提交" /> <input type="reset" value="重置" />
				</p>
			</form>
		</div>
	</div>
</body>
</html>
