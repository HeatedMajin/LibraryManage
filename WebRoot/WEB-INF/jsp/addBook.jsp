<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>添加书本</title>

<link rel="stylesheet" type="text/css" href="files/css/init.css">
<style type="text/css">
* {
	padding: 0px;
	margin: 0px;
}

input {
	font-family: "楷体";
	width: 350px;
	height: 24px;
	padding: 2px;
	border: 1px solid #fff;
	background: #fff;
	margin:3px 0px 20px 0px;
	letter-spacing: 1px;
}

body {
	background-color: #DFDFDF;
	font-family: "楷体";
}

#container {
	margin-top: 20px;
	width: 100%;
}

table {
	margin: 0 auto;
}

.btn {
	width: 150px;
	height: 30px;
	border: 2px solid #fff;
	background: #28B999;
	color: #fff;
	font-size: 18px;
	font-weight: 700;
	padding: 3px;
	transition: all 500ms;
	cursor: pointer;
	margin-left: 20px;
}

.btn:hover {
	background: #28B559;
	color: #222;
}
#btn_group{
	margin: 10px auto;
	text-align: center;
}
.title{
	margin-top:4px;
	font-size:18px;
}
.error{
	color:red;
	font-size:13px;
	font-family: "楷体";
}
#detail{
	font-size:14px;
	font-family:"楷体";
	resize: none;
	border: 0;
	padding:4px;
	line-height: 16px;
	letter-spacing: 1px;
}

</style>

</head>

<body>
	<div id="container" >
		<form action="${pageContext.request.contextPath }/addBookServlet" method="post">
			<table>
				<tr>
					<td>
						<span class="title">id号:</span>
					</td>
					<td>
						<input type="text" name="id" value="${bookBean.id}"/>
					</td>
					<td>
						<span class="error">${errors.id}</span>
					</td>
				</tr>
				<tr>
					<td>
						<span class="title">书本名:</span>
					</td>
					<td>
						<input type="text" name="name" value="${bookBean.name}">
					</td>
					<td>
						<span class="error">${errors.name}</span>
					</td>
				</tr>
				<tr>
					<td>
						<span class="title">作者:</span>
					</td>
					<td>
						<input type="text" name="author" value="${bookBean.author}">
					</td>
					<td>
						<span class="error">${errors.author}</span>
					</td>
				</tr>
				<tr>
					
					<td>
						<span class="title">出版日期:</span>
					</td>
					<td>
						<input type="date" name="publishDate" value="${bookBean.publishDate}">
					</td>
					<td>
					
						<span class="error">${errors.publishDate}</span>
					</td>
				</tr>
				<tr>
					<td rowspan="4">
						<span class="title">书本简介:</span>
					</td>
					<td>
					
						<textarea rows="6" cols="50" name="detail" id="detail" >${bookBean.detail}</textarea>
					</td>
					<td>
						<span class="error"></span>
					</td>
				</tr>
			</table>
			<div id="btn_group">
				<input class="btn" type="reset" value="重填"/> 
				<input class="btn" type="submit" value="提交"/>
			</div>
		</form>
	</div>
</body>

</html>
