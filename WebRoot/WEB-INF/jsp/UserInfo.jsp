<%@ page language="java" import="java.util.*" 	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>信息查看</title>
<link type="text/css" rel="stylesheet"	href="${pageContext.request.contextPath}/files/css/register2.css">
</head>
<body>
	<div class="register">
		<div class="r_content">
			<h1>用户信息</h1>
			<table>
				<dl>
					<dt>您的账号：</dt>
					<dd>
						<input type="text" readonly="readonly" value="${user.username}" >
					</dd>
				</dl>
				<dl>
					<dt>您的性别：</dt>
					<dd>
						<input type="text" value="${user.gender=='male'?'男':'女'}"readonly="readonly">
					</dd>
				</dl>
				<dl>
					<dt>您的爱好：</dt>
					<dd>
						<input type="text" value="${user.preference }"readonly="readonly">
					</dd>
				</dl>
				
				<dl>
					<dt>您的手机：</dt>
					<dd>
						<input type="text" value="${user.phone}" readonly="readonly">
					</dd>
				</dl>
				<dl>
					<dt>您的生日：</dt>
					<dd>
						<input type="text"value="${user.birthday}" readonly="readonly">
					</dd>
				</dl>
				<dl>
					<dt>您的邮箱：</dt>
					<dd>
						<input type="text" 	value="${user.email}" readonly="readonly">
					</dd>
				</dl>
			</table>
		</div>
	</div>


</body>
</html>