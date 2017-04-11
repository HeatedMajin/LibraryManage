<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.majin.cn" prefix="my"%>
<!DOCTYPE HTML>
<html>
<head>
<title>显示全部用户借阅的全部书籍</title>
<style type="text/css">
#container {
	text-align: center;
	font-family: "微软雅黑";
	font-size: 13px;
}

#table {
	width: 1100px;
	margin: 20px auto;
}

.odd {
	background-color: #F0ECE0;
}

tr:HOVER {
	background-color: #60ffa0;
}
td{
	padding:5px 2px;
}
</style>
</head>
<body>
	<div id="container">
		<table border="1" id="table">
			<tr>
				<td>书本的ID</td>
				<td>书本的名字</td>
				<td>借阅人</td>
				<td>借阅时间</td>
				<td>书本的作者</td>
				<td>出版日期</td>
				<td>书本简介</td>
			</tr>
			<c:forEach var="b" items="${books}" varStatus="status">
				<tr class="${status.count%2==0?'even':'odd' }">
					<td>${b.id}</td>
					<td>${b.name}</td>
					<td>被${b.borrow.borrowedBy}借阅</td>
					<td>${b.borrow.borrowDate}</td>
					<td>${b.author}</td>
					<td>${b.publishDate}</td>
					<td><my:hideMore content="${b.detail}" />${content}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
