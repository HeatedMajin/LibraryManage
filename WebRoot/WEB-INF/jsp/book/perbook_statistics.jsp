<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<title>报表</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/files/css/init.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/files/js/ichart.1.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/files/js/jquery-1.4.3.min.js"></script>
<script type="text/javascript">

	 $(function(){
					new iChart.Column3D({
						render : 'canvasDiv',
						data: ${data},
						title : '每本书本的借阅统计',
						width : 1180,
						height : 640,
						align:'left',
						offsetx:50,
						offsety:-30,
						legend : {
							enable : true
						},
						sub_option:{
							label:{
								color:'#111111'
							}
						},
						coordinate:{
							wall_style:[
								{color:"#333333"},
								{color:"#b2b2d3"},
								{color:"#a6a6cb"},
								{color:"#333333"},
								{color:"#74749b"},
								{color:"#a6a6cb"}
							],
							width:940,
							scale:[{
								 position:'left',	
								 start_scale:0,
								 end_scale:40,
								 scale_space:8,
								 listeners:{
									parseText:function(t,x,y){
										return {text:t+"次"}
									}
								}
							}]
						},
						animation:true
					}).draw();
			});
        </script>
<style type="text/css">
#canvasDiv {
	border: 0;
	padding: 0;
	margin: 10px auto;
	color: white;
}
</style>
</head>
<body>
	<div id="canvasDiv"></div>
</body>