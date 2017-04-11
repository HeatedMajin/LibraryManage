<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>筛选查询</title>
<link rel="stylesheet" type="text/css" href="files/css/init.css">
<style type="text/css">
* {
	padding: 0px;
	margin: 0px;
}

input {
	width: 100px;
	height: 20px;
	padding: 2px 4px;
	border: 1px solid #fff;
	background: #fff;
	margin:6px 6px 6px 4px;
}

body {
	background-color: #f0f0f0;
	font-size: 14px;
	font-family: "微软雅黑";
}

#container {
	margin-top: 10px;
	width: 100%;
}

table {
	margin: 0 auto;
}

input[type="date"] {
	width: 150px;
}

input[type="submit"] {
	width: 150px;
	height: 35px;
	border: 2px solid #fff;
	background: #28B999;
	color: #fff;
	font-size: 18px;
	font-weight: 700;
	padding: 3px;
	transition: all 500ms;
	cursor: pointer;
}

input[type="submit"]:hover {
	background: #28B559;
	color: #222;
}

</style>

</head>

<body>
	<div id="container">
		<form action="${pageContext.request.contextPath}/searchBookServlet?type=filter"
			target="main" method="post">
			<table cellpadding="40%" cellspacing="40">
				<tr>
					<td>
						id号<input type="text" name="id">
					</td>
					<td>
						书名<input class="input" type="text" name="name">
					</td>
					<td>
						作者<input class="input" type="text" name="author">
					</td>
					<td>
						出版日期<input class="date" type="date" name="publishDate1" />
					</td>
					<td>
						~<input class="date" type="date" name="publishDate2" />
					</td>
					<td colspan="5">
						<input type="submit" value="查询" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
