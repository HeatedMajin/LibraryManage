<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.majin.cn" prefix="my" %>
<html>
<head>

<title>显示所有的用户</title>
<style type="text/css">
#container {
	text-align: center;
	font-family: "微软雅黑";
	font-size: 12px;
}

#table {
	width: 1000px;
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
				<td>用户的名字</td>
				<td>用户的原有身份</td>
				<td>用户的性别</td>
				<td>用户的邮箱</td>
				<td>用户的手机</td>
				<td>操作</td>
			</tr>
			<c:forEach var="u" items="${users}" varStatus="status">
				<tr class="${status.count%2==0?'even':'odd' }">
					<td>
						<my:InListRed list="${shutnames}" target="${u.username}">
							${content}
						</my:InListRed>
					</td>
					<td><c:forEach items="${u.roles}" var="list_user_role">
							${list_user_role.name}
						</c:forEach></td>
					<td>${u.gender}</td>
					<td>${u.email}</td>
					<td>${u.phone}</td>
					<td><c:url var="url" value="/servlet/ManageServlet">
							<c:param name="uname" value="${u.username}"></c:param>
						</c:url> <a href="${url}&todo=fenghao">封号</a> <a
						href="${url}&todo=jiefeng">解封</a> <a href="${url}&todo=shouquan">授权</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>