<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>修改密码</title>

<link rel="stylesheet" type="text/css" href="files/css/init.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/files/css/register.css">
<style type="text/css">
body {
	height: 600px;
	overflow:hidden;
}

.register {
	height: 360px;
}

.r_content {
	height: 260px;
}

input[type='submit'] {
	width: 310px;
}

.error {
	font-family: serif; font-size : 12px;
	font-weight: bold;
	color: #FC4343;
	font-size: 13px;
}
</style>
</head>

<body>

	<div class="register">
		<div class="r_content">
			<h1>修改密码</h1>
			<form id="form"
				action="${pageContext.request.contextPath}/changPasswordServlet"
				method="post">
				<dl>
					<dt>请输入您的旧密码：</dt>
					<dd>
						<input type="password" placeholder="请输入您的旧密码..."
							name="oldpassword" />
					</dd>
					<dd>
						<span class="error"> ${p_errors.oldpassword}</span>
					</dd>
				</dl>
				<dl>
					<dt>请输入您的新密码：</dt>
					<dd>
						<input type="password" placeholder="请输入您的新密码..."
							name="newpassword" />
					</dd>
					<dd>
						<span class="error"> ${p_errors.newpassword}</span>
					</dd>
				</dl>
				<dl>
					<dt>再输入您的新密码：</dt>
					<dd>
						<input type="password" placeholder="请再次输入您的新密码..."
							name="confirmpassword" />
					</dd>
					<dd>
						<span class="error"> ${p_errors.confirmpassword}</span>
					</dd>
				</dl>
				<dl>
					<dt>亲，可以提交咯：</dt>
					<dd>
						<input type="submit" value="确认修改">
					</dd>
				</dl>
			</form>
		</div>
	</div>

</body>
</html>
