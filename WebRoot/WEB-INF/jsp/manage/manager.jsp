<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<title>后台管理界面</title>
</head>
<frameset rows="77px,*" frameborder="no" border="0" framespacing="0">
	<frame src="${pageContext.request.contextPath}/managertop.jsp" name="top" scrolling="no" noresize="noresize"></frame>
	<frameset cols="180px,*" frameborder="yes" border="2px"framespacing="0">
		<frame src="${pageContext.request.contextPath}/managerleft.jsp" name="left" scrolling="no" noresize="noresize"></frame>
		<frame src="" name="right" scrolling="no" noresize="noresize"></frame>
	</frameset>
</frameset>
</html>
