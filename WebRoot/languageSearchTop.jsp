<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>自然语言查询</title>
<link rel="stylesheet" type="text/css" href="files/css/init.css">
<style type="text/css">
* {
	padding: 0px;
	margin: 0px;
}

input {
	width: 450px;
	height: 24px;
	padding: 2px 8px;
	border: 1px solid #fff;
	background: #fff;
	margin: 10px 0;
	letter-spacing: 1px;
}

body {
	background-color: #DFDFDF;
	font-size: 16px;
	font-family: "微软雅黑";
}

#container {
	margin: 3px auto;
	width: 100%;
	text-align: center;
}

input[type="submit"] {
	width: 120px;
	height: 32px;
	border: 1px solid #fff;
	background: #28B999;
	color: #fff;
	font-size: 18px;
	font-weight: 500;
	padding: 2px;
	transition: all 500ms;
	cursor: pointer;
	letter-spacing: 6px;
}

input[type="submit"]:hover {
	background: #28B559;
	color: #222;
}

form {
	margin: 0 auto;
}
</style>
</head>

<body>
	<div id="container">
		<form
			action="${pageContext.request.contextPath}/searchBookServlet?type=language"
			target="main" method="post">
			<input type="text" placeholder="输入自然语言" name="str"> <input
				type="submit" value="查询">
		</form>
	</div>
</body>
</html>
