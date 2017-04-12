<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" type="text/css" href="files/css/init.css">
<style type="text/css">
#container {
	width: 700px;
	font-size: 14px;
	margin:0 auto;
	letter-spacing: 1px;
}

.result_item {
	margin: 20px 0px 10px 0px;
	width: 540px;
}

.t {
	font-weight: 400;
	font-size: medium;
	margin-bottom: 2px;
}

a:-webkit-any-link {
	color: -webkit-link;
	text-decoration: underline;
	cursor: auto;
}

p {
	font-size: 13px;
	line-height: 15px;
	font-family: serif;
	color: #7f7f7f;
	margin:2px;
}

.text {
	font-size: 14px;
	font-family: "微软亚黑";
	letter-spacing: 1px;
	color: black;
}

#page{
	letter-spacing: 1px;
}
</style>

<script type="text/javascript">
	function changePage(target){
		//location.href = "${pageContext.request.contextPath}/searchBookServlet?type=post&currentPage="+target;
		var hidden = document.getElementById("hidden");
		hidden.value=target;
		document.form4post.submit();		
	}
</script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/searchBookServlet" method="post" name="form4post" id="form4post" style='display:none'>
		<input id="hidden" type="hidden" name='currentPage' >
		<input id='a' type='hidden' name='id' value='${condition.id }'>
		<input id='b'type='hidden' name='name' value='${condition.name}'>
		<input id='c'type='hidden' name='author' value='${condition.author}'>
		<input id='d'type='hidden' name='publishDate1' value='<fmt:formatDate value='${condition.publishDate1}' pattern='yyyy-MM-dd'/>'>
		<input id='e'type='hidden' name='publsihDate2' value='<fmt:formatDate value='${condition.publishDate2}' pattern='yyyy-MM-dd'/>'>
	</form>
	<div id="container">
		<c:forEach items="${pageBean.context}" var="book">
			<div class="result_item">
				<h3 class="t">
					<a href="${pageContext.request.contextPath}/showBookServlet?id=${book.id}" target="_parent">《${book.name}》 —— (作者：${book.author}) ......_百度不知道</a>
				</h3>
				<p>1个书本 - 出版时间:${book.publishDate}</p>
				<div class="text">${book.detail}</div>
			</div>
		</c:forEach>
		<br/>
		<span id="page">
		共[${pageBean.totalPage}]页,
		每页[${pageBean.pageSize}]数据,
		当前第[${pageBean.currentPage}]页
		<a href="javascript:void(0)" onclick="changePage(${pageBean.previousPage})">上一页</a>
			<c:forEach items="${pageBean.p}" var="pp">
				<c:if test="${pp==pageBean.currentPage}">
					<font color="red">${pp}</font>
				</c:if>
				<c:if test="${pp!=pageBean.currentPage}">
					<a href="javascript:void(0)" onclick="changePage(${pp})" >${pp}</a>
				</c:if>
			</c:forEach>
		<a href="javascript:void(0)" onclick="changePage(${pageBean.nextPage})">下一页</a>
		</span>
		<br/><br/><br/>
	</div>
</body>
</html>