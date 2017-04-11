<%@ page language="java" pageEncoding="UTF-8" import="java.lang.*"%>
<%@	taglib uri="http://www.majin.cn" prefix="my"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<style>
body {
	margin: 0;
	padding: 0 20px;
	font-size: 12px;
	font-family: 微软雅黑;
	background: #0767C8;
	color: white;
}

#container {
	margin: 10px;
	height: 28px;
	background: url(files/images/logo_sd.png) no-repeat;
}

#container #title {
	font-size: 28px;
	font-family: STKaiti, 楷体;
	font-weight: bold;
	color: white;
	margin-left: 120px;
	float: left;
}

.text {
	margin: 10px;
	color: white;
	float: right;
}

#zhanghao{
	font-size: 12px;
	margin-top: 15px;
}
#houtai {
	font-size: 12px;
	margin-top: 14px;
	float: left;
}

a {
	text-decoration: none;
}

a:link {
	color: white;
}

a:VISITED {
	color: white;
}

a:hover {
	text-decoration: underline;
	color: orange;
}
</style>
</head>
<body>
	<div id="container">
		<div id="title">山东大学图图书馆</div>

		<div id="zhanghao" class="text">
			<span>欢迎您：${user.username} &nbsp;</span> <a
				href="${pageContext.request.contextPath}/servlet/LogoutServlet"
				target="_top">-注销-</a>
		</div>


		<div id="houtai" class="text">
			<my:showOrNot allowRoleName="数据库管理员">
				<a
					href="${pageContext.request.contextPath}/servlet/ManageServlet?todo=manageUI"
					target="_top">-->进入后台</a>
			</my:showOrNot>
		</div>

	</div>
</body>
</html>