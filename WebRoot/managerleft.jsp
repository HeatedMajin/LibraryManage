<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<title>后台管理界面</title>

<link rel="stylesheet" type="text/css" href="files/css/init.css">

<style type="text/css">
body {
	width: 180px;
	margin: 0;
	padding: 0;
	background-color: #E6E6E6;
	font-size: 14px;
	font-family: "微软雅黑";
}

.group {
	background-color: white;
	border-radius: 10px 10px 10px 10px;
	margin-top: 16px;
	margin-left: 10px;
	margin-right: 10px;
}

.title {
	font-size: 15px;
	padding: 4px;
	color: black;
	letter-spacing: 1px;
	border-bottom: 1px dashed #E6E6E6;
}

.title div a {
	text-decoration: none;
}

.title div a:hover {
	text-decoration: underline;
	color: orange;
}

.title #userlogo {
	width: 27px;
	height: 27px;
	background: url(files/images/userlogo.png) no-repeat;
	float: left;
}

.title #booklogo {
	width: 27px;
	height: 27px;
	background: url(files/images/booklogo.png) no-repeat;
	margin-left: 3px;
	float: left;
}

.content {
	margin-left: 30px;
	margin-top: 6px;
	padding: 4px;
}

.content li {
	margin-bottom: 6px;
}

.content li a {
	font-size: 12px;
	text-decoration: none;
}

.content li a:hover {
	text-decoration: underline;
	font-size: 13px;
	color: orange;
}

#one {
	margin-top: 20px;
}
</style>

</head>
<body>
	<div id="container">
		<div id="one" class="group">
			<div class="title">
				<div id="userlogo"></div>
				<div>
					<a href="javascript:void(0)">管理操作</a>
				</div>
			</div>
			<div class="content">
				<li><a
					href="${pageContext.request.contextPath }/servlet/ManageServlet?todo=checkDBchange"
					target="right">查看图书馆的变动</a></li>
				<li><a
					href="${pageContext.request.contextPath }/servlet/ManageServlet?todo=listalluser"
					target="right">查看所有用户</a></li>
			</div>
		</div>

		<div id="two" class="group">
			<div class="title">
				<div id="userlogo"></div>
				<div>
					<a href="javascript:void(0)">待增加功能</a>
				</div>
			</div>
			<div class="content">
				<li><a href="#" target="right">待增加功能1</a></li>
				<li><a href="#" target="right">待增加功能2</a></li>
			</div>
		</div>
		
		
		
		<div id="three" class="group">
			<div class="title">
				<div id="userlogo"></div>
				<div>
					<a href="javascript:void(0)">待增加功能</a>
				</div>
			</div>
			<div class="content">
				<li><a href="#" target="right">待增加功能3</a></li>
				<li><a href="#" target="right">待增加功能4</a></li>
			</div>
		</div>
	</div>
</body>
</html>
