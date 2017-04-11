<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />

<title>登录界面</title>

<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	background-color: #F2F2F2;
	overflow: hidden;
	font-family: "微软雅黑";
	font-size: 14px;
}

#container {
	width: 1002px;
	margin: 0 auto;
}

#header {
	width: 1000px;
	height: 110px;
	background: url(files/images/sd_logo.png) no-repeat;
	margin-top: 30px;
}

#header #welcome {
	font-size: 30px;
	color: #3F3F40;
	margin-left: 240px;
}

#bodyer {
	width: 900px;
	height: 350px;
	border: 1px solid #E8E8E8;
	background: white;
}

#bodyer #img {
	width: 500px;
	height: 350px;
	background: url(files/images/tp4.png) no-repeat;
	float: left;
	position: relative;
}

#bodyer #sd {
	font-size: 40px;
	color: white;
	position: absolute;
	left: 40px;
	top: 60px;
}

#bodyer #booksystem {
	font-size: 38px;
	color: white;
	position: absolute;
	left: 40px;
	top: 130px;
}

#bodyer #form {
	width: 400px;
	height: 350px;
	float: left;
}

#bodyer #form #message {
	margin-left: 100px;
	margin-top: 60px;
}

#bodyer #form #error {
	width: 300px;
	margin: 0 auto;
	height: 20px;
	/*错误信息*/
}

#bodyer #form form {
	width: 300px;
	margin-top: 20px;
	margin: 0 auto;
}

#bodyer #form form div {
	margin-top: 8px;
}

.input input {
	font-size: 16px;
	border: 1px solid #488EE7;
	background: #FAFFBD;
	width: 230px;
	height: 26px;
	padding: 2px 6px;
	letter-spacing: 1px;
}

#bodyer #form form #login {
	margin-top: 14px;
}

.btn input {
	font: bold 16px "微软雅黑";
	color: white;
	padding: 4px;
	border: 0px;
	width: 285px;
	height: 32px;
	background: #2E82FF;
	letter-spacing: 10px;
}

.btn input:HOVER {
	cursor: pointer;
}

#footter {
	width: 1000px;
	height: 100px;
	font-size: 12px;
	text-align: center;
	margin-top: 100px;
}

#remember {
	font-size: 12px;
	line-height: 16px;
	padding: 1px;
}

input[type='checkbox'] {
	padding: 0px;
	margin-top: 2px;
	height: 16px;
	width: 16px;
}

input[type='checkbox']:HOVER {
	cursor: pointer;
}

#error {
	color: red;
}
</style>

</head>
<body>
	<div id="container">
		<div id="header">
			<span id="welcome">| 欢迎登录图书信息管理系统</span>
		</div>
		<div id="bodyer">
			<div id="img">
				<span id="sd">山东大学</span> <span id="booksystem">图书信息管理系统</span>
			</div>
			<div id="form">
				<div id="message">
					<h2>登录账号</h2>
				</div>
				<div id="error">${error}</div>
				<!-- form提交的位置 -->
				<form action="${pageContext.request.contextPath}/loginServlet"
					method="post">
					<div id="user" class="input">
						用户：<input type="text" name="username"
							value="${formbean.username }" />
					</div>
					<div id="password" class="input">
						密码：<input type="password" name="password"
							value="${formbean.password }" />
					</div>
					<div id="remember">
						记住我：<input type="checkbox" name="remember" value="remember"
							${formbean.remember=='remember'?'checked':''} />
					</div>
					<div id="login" class="btn">
						<input type="submit" value="登录" />
					</div>
					<div id="register" class="btn">
						<input type="button" value="注册"
							onclick="javascrtpt:window.location.href='${pageContext.request.contextPath}/register'" />
					</div>
				</form>
			</div>
		</div>
		<div id="footter">
			<span>Copyright © 2015 © SD大学计算机中心</span>
		</div>
	</div>
</body>
</html>