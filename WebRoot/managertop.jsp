<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<title>后台管理界面</title>
<style type="text/css">
*{
	margin:0px;
	padding:0px;
}
#title {
	font-size: 12px;
	font-family: 微软雅黑;
	background-color: #E7E9E7;
	color:black;
	padding:6px;
}
h1{
	margin:8px;
}
a {
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
	color: orange;
}

a:link {
	color: black;
}

a:visited{
	color: black;
}

</style>
</head>
<body style="text-align: center;">
	<div id="title">
		<h1>图书馆_后台管理界面</h1>
		<span>欢迎您：${user.username} &nbsp;</span>
		<a	href="${pageContext.request.contextPath}/servlet/LogoutServlet"
			target="_top">-注销-</a>
	</div>
</body>
</html>
