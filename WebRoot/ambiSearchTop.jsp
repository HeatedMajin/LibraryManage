<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>

<title>模糊提示搜素条</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/files/css/reset-grids-min.css"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/files/js/yahoo-dom-event.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/files/js/suggest.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/files/js/sys.js"></script>

<style type="text/css">
#page {
	text-align: center;
	padding: 4px 20px 300px;
	margin: 0 auto;
	background-color: #EFEFEF;
	font-size: 14px;
	font-family: "微软雅黑";
}

#h1, h2, h3 {
	margin: 1em 0 0.3em;
}

#
.section {
	margin-bottom: 50px;
}

#
.section ol {
	margin: 5px 20px;
}

#
.section ol li {
	list-style: decimal inside;
	margin: 5px 0;
}

.search-input {
	width: 300px;
	height: 20px;
	padding: 5px 2px 0 4px;
}

#
.search-submit {
	padding: 4px 10px;
	margin-left: 5px;
}

#input.g-submit {
	padding: 2px 8px;
	margin-left: 5px;
}

#html {
	overflow-y: scroll;
}

* {
	padding: 0px;
	margin: 0px;
}

input {
	width: 450px;
	height: 24px;
	padding: 1px 8px;
	border: 1px solid #fff;
	background: #fff;
	margin: 4px 0;
	letter-spacing: 1px;
}

#container {
	margin: 3px auto;
	width: 100%;
	text-align: center;
}

input[type="submit"] {
	width: 110px;
	height: 28px;
	border: 1px solid #fff;
	background: #28B999;
	color: #fff;
	font-size: 18px;
	font-weight: 500;
	padding: 2px;
	transition: all 500ms;
	cursor: pointer;
	letter-spacing: 6px;
}

input[type="submit"]:hover {
	background: #28B559;
	color: #222;
}

form {
	margin: 0 auto;
}

#text {
	width: 600px;
	margin: 0 auto;
	text-align: left;
	font-family: "微软雅黑";
	color: gray;
	letter-spacing: 1px;
	line-height: 18px;
	letter-spacing: 1px;
}
</style>
</head>
<body>
	<div id="page">
		<div class="section">
			<form name="search" method="post" target="main"
				action="${pageContext.request.contextPath}/searchBookServlet?type=ambigous">
				<input name="q" id="q" class="search-input" /> <input type="submit"
					class="search-submit" value="搜索书本"></input>
			</form>
			<script type="text/javascript">
				(function() {
					var dataUrl = '${pageContext.request.contextPath}/servlet/SearchSupportServlet';
					new KISSY.Suggest('q', dataUrl,
						{
							autoFocus : true,
							resultFormat : '共%result%本书'
						});
				})();
			</script>
		</div>
		<div id="text">
			<p>&nbsp;&nbsp;&nbsp;&nbsp;输入书本的某一段名称，将自动补全您想要搜索的书籍名称。在下拉列表中,选取您的想要查找的书籍，即可完成查询。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;如：输入"从入门到精通"，提示"java从入门到精通、c++从入门到精通、JavaWeb从入门到精通、LOL从入门到精通"</p>
		</div>
	</div>
</body>
</html>
