<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.majin.cn" prefix="my"%>
<!DOCTYPE HTML>
<html>
<head>

<title>显示书本的详细信息</title>

<style type="text/css">
* {
	padding: 0px;
	margin: 0px;
}

body {
	font-family: "微软雅黑";
	font: 14px/50px "微软雅黑";
	background: url("files/images/bj.jpg") no-repeat top center;
	background-attachment: fixed;
	color: #fff;
	perspective: 3000px;
	-webkit-perspective: 3000px;
	overflow: hidden;
}

.register {
	width: 700px;
	height: 460px;
	background: rgba(190, 190, 190, 0.5);
	margin: 30px auto;
	transform-style: preserve-3D;
	-webkit-transform-style: preserve-3D;
	-webkit-transform: rotateX(50deg) rotateZ(-30deg);
	-moz-transform: rotateX(50deg) rotateZ(-30deg);
	-o-transform: rotateX(50deg) rotateZ(-30deg);
	transition: all 2s;
	-webkit-transition: all 2s;
	position: relative;
}

.register:hover {
	transform: rotateX(0deg) rotateZ(0deg);
	-webkit-transform: rotateX(0deg) rotateZ(0deg);
}

.register .r_content {
	width: 850px;
	height: 550px;
	padding: 15px 0;
}

.r_content:before, .r_content:after {
	content: "";
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background: rgba(250, 250, 250, 0.6)
}

.r_content:before {
	transform-origin: left center;
	-webkit-transform-origin: left center;
	transform: rotateY(90deg);
	-webkit-transform: rotateY(90deg);
	width: 25px;
}

.r_content:after {
	top: auto;
	left: 0px;
	transform-origin: bottom center;
	-webkit-transform-origin: bottom center;
	transform: rotateX(90deg);
	-webkit-transform: rotateX(90deg);
	height: 25px;
}

.register .r_content tr {
	height: 40px;
	line-height: 40px;
	margin: 20px 0px;
}

.register .r_content td {
	width: 180px;
	text-align: right;
	float: left;
}

.register .r_content dd {
	width: 320px;
	float: left;
}

input {
	border: 1px solid gray;
	color: #fff;
	background-color: #5F5F5F;
	margin-left: 10px;
	width: 300px;
	height: 24px;
	padding: 5px;
	font-family: "微软雅黑";
}

textarea {
	resize: none;
	font-size: 14px;
	font-family: "微软雅黑";
	color: white;
	letter-spacing: 1px;
	background-color: #5F5F5F;
	padding: 4px;
	border: 1px solid gray;
	display: block;
	margin: 10px 4px;
}

input[type="button"] {
	clear: both;
	width: 140px;
	height: 30px;
	border: 2px solid #fff;
	background: #28B999;
	color: #fff;
	font-size: 18px;
	font-weight: 700;
	padding: 2px;
	transition: all 500ms;
	cursor: pointer;
}

input[type="button"]:hover {
	background: #28B559;
	color: #222;
}

#btn_group {
	float: left;
	text-align: center;
	margin: 6px 130px;
}

#btn_group input {
	margin-right: 20px;
}

#clear {
	clear: both;
}
</style>

<script type="text/javascript">
	function doDel(){
		var id=${bookDetail.id};
		location.href = "${pageContext.request.contextPath}/delBookServlet?id="+id;
	}
	function doUpdate(){
		var id=${bookDetail.id};
		location.href = "${pageContext.request.contextPath}/updateBookServlet?id="+id;
	}
	function doBorrow(){
		var id=${bookDetail.id};
		location.href = "${pageContext.request.contextPath}/servlet/BorrowBookServlet?book_id="+id;
	}
</script>
</head>

<body>
	<div class="register">
		<div class="r_content">
			<table>
				<tr>
					<td>书本ID：</td>
					<td><input type="text" readonly="readonly"
						value="${bookDetail.id}"></td>
				</tr>

				<tr>
					<td>书本名称：</td>
					<td><input type="text" value="${bookDetail.name}"
						readonly="readonly"></td>
				</tr>
				<tr>
					<td>书本作者：</td>
					<td><input type="text" value="${bookDetail.author}"
						readonly="readonly"></td>
				</tr>
				<tr>
					<td>是否可借阅</td>
					<td><c:if test="${bookDetail.borrow==null}">
							<input type="text" value="可借阅" readonly="readonly">
						</c:if> 
						<c:if test="${bookDetail.borrow!=null}">
							<input type="text" value="被${bookDetail.borrow.borrowedBy}借阅，借阅时间${bookDetail.borrow.borrowDate}"
								readonly="readonly">
						</c:if>
					</td>
				</tr>
				<tr>
					<td>出版日期：</td>
					<td><input type="text" value="${bookDetail.publishDate}"
						readonly="readonly"></td>
				</tr>
				<tr>
					<td>书本简介：</td>
					<td><textarea readonly="readonly" cols="50" rows="7">${bookDetail.detail}</textarea>
					</td>
				</tr>
			</table>
			<div id="clear"></div>
			<div id="btn_group">
				<my:showOrNot allowRoleName="图书管理员">
					<input type="button" value="删除" onclick="doDel()" />
					<input type="button" value="修改" onclick="doUpdate()" />
				</my:showOrNot>
				<my:showOrNot allowRoleName="学生">
					<c:if test="${bookDetail.borrow==null}">
						<input type="button" value="借阅" onclick="doBorrow()" />
					</c:if>
				</my:showOrNot>
			</div>
		</div>
	</div>


</body>
</html>

