<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>更改用户的角色</title>
<style type="text/css">
#container {
	text-align: center;
	font-family: "微软雅黑";
	font-size: 18px;
}

#table {
	width: 700px;
	margin: 20px auto;
}


input[type="submit"] {
	width: 150px;
	height: 30px;
	border: 2px solid #fff;
	background: #28B999;
	color: #fff;
	font-size: 18px;
	font-weight: 700;
	padding: 1px;
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
	<form action="${pageContext.request.contextPath}/servlet/ManageServlet" method="post">
		<table border="1" id="table">
			<tr>
				<td>用户名</td>
				<td>
				<input type="hidden" name="username" value="${changeuser.username}">
				${changeuser.username}
				</td>
			</tr>
			
			<tr>
				<td>原有的角色</td>
				<td>
					<c:forEach var="prerole" items="${changeuser.roles}">
						${prerole.name}
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>更新角色</td>
				<td>
					<c:forEach var="r" items="${allroles}">
						${r.name }<input type="checkbox" name="newrole" value="${r.id}"><br>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="提交">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>
