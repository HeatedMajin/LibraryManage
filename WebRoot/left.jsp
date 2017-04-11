<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.majin.cn" prefix="my"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />

<link rel="stylesheet" type="text/css" href="files/css/init.css">

<style type="text/css">
body {
	width: 180px;
	margin: 0;
	padding: 0;
	background-color: #DFDFDF;
	font-size: 14px;
	font-family: "微软雅黑";
}

.group {
	background-color: white;
	border-radius: 10px 10px 10px 10px;
	margin-top: 10px;
	margin-left: 10px;
	margin-right: 10px;
}

.title {
	font-size: 15px;
	padding: 7px 4px;
	color: gray;
	letter-spacing: 1px;
	border-bottom: 1px dashed #E6E6E6;
}

.title div a {
	text-decoration: none;
}

.title div a:hover {
	text-decoration: underline;
	color: orange;
}

.logo {
	width: 27px;
	height: 27px;
	float: left;
}

#userlogo {
	background: url(files/images/userlogo.png) no-repeat;
	margin-left: -2px;
}

#booklogo {
	background: url(files/images/booklogo.png) no-repeat;
	margin-left: 3px;
}

#privilegelogo {
	background: url(files/images/privilegelogo.png) no-repeat;
	margin-left: 2px;
}

.content {
	margin-left: 30px;
	margin-top: 6px;
	padding: 4px;
	display: none;
}

.content li {
	margin-bottom: 6px;
}

.content li a {
	font-size: 12px;
	text-decoration: none;
}

.content li a:hover {
	text-decoration: underline;
	font-size: 13px;
	color: orange;
}

#one {
	margin-top: 30px;
}
</style>

<script>
	function hiddenDiv(div) {
		//把其他的content关闭
		var tags = document.getElementsByClassName("content");
		for (var i = 0; i < tags.length; i++) {
			if (tags[i].getAttribute("id") != div.getAttribute("id")) {
				tags[i].style.display = 'none';
			}
		}
		//打开或关闭当前这个content
		div.style.display = (div.style.display == 'none' ? 'block' : 'none');
	}
</script>
</head>
<body>
	<div id="container">
		<div id="one" class="group">
			<div class="title">
				<div id="userlogo" class="logo"></div>
				<div>
					<a href="javascript:void(0)"
						onclick="hiddenDiv(document.getElementById('c1'))">个人信息</a>
				</div>
			</div>
			<div class="content" id="c1">
				<ul>
					<li><a
						href="${pageContext.request.contextPath }/showUserServlet"
						target="right">查看用户信息</a></li>
					<li><a
						href="${pageContext.request.contextPath }/editUserServlet"
						target="right">修改用户信息</a></li>
					<li><a
						href="${pageContext.request.contextPath }/changPasswordServlet"
						target="right">修改用户密码</a></li>
				</ul>
			</div>
		</div>

		<div id="two" class="group">
			<div class="title">
				<div id="booklogo" class="logo"></div>
				<div>
					<a href="javascript:void(0)"
						onclick="hiddenDiv(document.getElementById('c2'))">书本查询</a>
				</div>
			</div>
			<div class="content" id="c2">
				<li><a
					href="${pageContext.request.contextPath }/searchBookServlet?type=filter"
					target="right">筛选查询</a></li>
				<li><a
					href="${pageContext.request.contextPath }/searchBookServlet?type=language"
					target="right">自然语言查询</a></li>
				<li><a
					href="${pageContext.request.contextPath }/searchBookServlet?type=ambigous"
					target="right">模糊提示查询</a>
			</div>
		</div>


		<div id="three" class="group">
			<div class="title">
				<div id="booklogo" class="logo"></div>
				<div>
					<a href="javascript:void(0)"
						onclick="hiddenDiv(document.getElementById('c3'))">书本操作</a>
				</div>
			</div>
			<div class="content" id="c3">
				<ul>

					<li><a
						href="${pageContext.request.contextPath }/servlet/ShowBorrowServlet?todo=my"
						target="right">查看自己的借阅</a></li>
					<my:showOrNot allowRoleName="图书管理员">
						<li><a
							href="${pageContext.request.contextPath }/servlet/ShowBorrowServlet?todo=all"
							target="right">查看所有的借阅</a></li>
						<li><a
							href="${pageContext.request.contextPath }/addBookServlet"
							target="right">添加书本</a></li>

					</my:showOrNot>
				</ul>
			</div>
		</div>
		<my:showOrNot allowRoleName="图书管理员">
			<div id="six" class="group">
				<div class="title">
					<div id="booklogo" class="logo"></div>
					<div>
						<a href="javascript:void(0)"
							onclick="hiddenDiv(document.getElementById('c6'))">数据报表</a>
					</div>
				</div>
				<div class="content" id="c6">
					<ul>
						<li><a
							href="${pageContext.request.contextPath }/servlet/ShowStatisticsServlet?todo=permonth"
							target="right">本年份借阅统计</a></li>
						<li><a
							href="${pageContext.request.contextPath }/servlet/ShowStatisticsServlet?todo=perbook"
							target="right">书本借阅统计</a></li>
					</ul>
				</div>
			</div>
		</my:showOrNot>

		<div id="four" class="group">
			<div class="title">
				<div id="privilegelogo" class="logo"></div>
				<div>
					<a href="javascript:void(0)"
						onclick="hiddenDiv(document.getElementById('c4'))">申请权限</a>
				</div>
			</div>
			<div class="content" id="c4">
				<ul>
					<li><a href="${pageContext.request.contextPath }/talk.html"
						target="right">购买权限</a></li>
					<li><a href="${pageContext.request.contextPath }/talk.html"
						target="right">联系管理员</a></li>
				</ul>
			</div>
		</div>

		<div id="five" class="group">
			<div class="title">
				<div id="userlogo" class="logo"></div>
				<div>
					<a href="javascript:void(0)"
						onclick="hiddenDiv(document.getElementById('c5'))">关于项目 </a>
				</div>
			</div>
			<div class="content" id="c5">
				<ul>
					<li><a href="${pageContext.request.contextPath }/talk.html"
						target="right">浅谈项目</a></li>
					<li><a href="${pageContext.request.contextPath }/talk.html"
						target="right">意见反馈</a></li>
				</ul>
			</div>
		</div>


	</div>
</body>
</html>