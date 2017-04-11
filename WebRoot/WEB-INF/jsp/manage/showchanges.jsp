<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>显示数据库改动</title>
<link rel="stylesheet" type="text/css" href="files/css/init.css">
<style type="text/css">
#container {
	text-align: center;
	font-family: "微软雅黑";
	font-size: 12px;
}

#table {
	width: 800px;
	margin: 20px auto;
}

.odd {
	background-color: #F0ECE0;
}

tr:HOVER {
	background-color: #60ffa0;
}
</style>
</head>
<body>
	<div id="container">
		<table border="1" id="table">
			<tr>
				<td>修改的表</td>
				<td>修改信息</td>
				<td>修改用户</td>
				<td>修改时间</td>
			</tr>
			<c:forEach var="ch" items="${changes}" varStatus="status">
				<tr class="${status.count%2==0?'even':'odd' }">
					<td>${ch.op_table}</td>
					<td>${ch.op_type}</td>
					<td>${ch.op_user}</td>
					<td>${ch.op_date}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
