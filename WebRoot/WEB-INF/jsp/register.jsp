<%@ page language="java" import="java.util.*" 	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>免费注册</title>
<link type="text/css" rel="stylesheet"	href="${pageContext.request.contextPath}/files/css/register.css">
<script type="text/javascript">
	function generatePreference(){
		var pres=document.getElementsByClassName("pres");
		var preference = "";
		for(var i = 0 ; i < pres.length;i++){
			if(pres[i].checked){
				preference=preference+pres[i].value +",";
			}
		}
		/*到最后会多出一个,来需要戒掉*/
		preference = preference.substr(0, preference.length-1);
		
		//alert(preference);
		
		/*设置隐藏域确保preference回传递*/
		var form = document.getElementById("form");
		var hidden = document.createElement("input");
		hidden.type="hidden";
		hidden.name="preference";
		hidden.value=preference;
		form.appendChild(hidden);
	}
</script>	
</head>
<body>
	<div class="register">
		<div class="r_content">
			<h1>用户免费注册</h1>
			<form id="form" action="formcheck?type=register" method="post" onsubmit="generatePreference()">
				<dl>
					<dt>请输入您的账号：</dt>
					<dd>
						<input type="text" placeholder="请输入账号..." name="username"
							value="${form.username}">
					</dd>
					<dd>
						<span> ${errors.username}</span>
					</dd>
				</dl>
				<dl>
					<dt>请输入您的密码：</dt>
					<dd>
						<input type="password" placeholder="请输入您的密码..." name="password" />
					</dd>
					<dd>
						<span> ${errors.password}</span>
					</dd>
				</dl>
				<dl>
					<dt>再输入您的密码：</dt>
					<dd>
						<input type="password" placeholder="请再次输入您的密码..." name="password2" />
					</dd>
					<dd>
						<span> ${errors.password2}</span>
					</dd>
				</dl>
				<dl>
					<dt>请输入您的性别：</dt>
					<dd>
						<input type="radio" name="gender"value="male" ${form.gender=='male'?'checked':''}>男
						<input type="radio" name="gender" value="female" ${form.gender=='female'?'checked':''}>女
					</dd>
					<dd>
						<span> ${errors.gender}</span>
					</dd>
				</dl>
				<dl>
					<dt>请输入您的爱好：</dt>
					<dd>
						<c:forEach items="${preference}" var="pre">
							<input type="checkbox" class="pres" value="${pre}" ${fn:contains(form.preference,pre)?'checked':''}>${pre}
						</c:forEach>
					</dd>
				</dl>
				
				<dl>
					<dt>请输入您的手机：</dt>
					<dd>
						<input type="number" placeholder="请输入您的手机..." name="phone"
							value="${form.phone}">
					</dd>
					<dd>
						<span> ${errors.phone}</span>
					</dd>
				</dl>
				<dl>
					<dt>请输入您的生日：</dt>
					<dd>
						<input type="date" placeholder="请输入您的生日..." name="birthday"
							value="${form.birthday}">
					</dd>
					<dd>
						<span> ${errors.birthday}</span>
					</dd>
				</dl>
				<dl>
					<dt>请输入您的邮箱：</dt>
					<dd>
						<input type="Email" placeholder="请输入您的邮箱..." name="email"
							value="${form.email}">
					</dd>
					<dd>
						<span> ${errors.email}</span>
					</dd>
				</dl>
				<dl>
					<dt>亲，可以提交咯：</dt>
					<dd>
						<input type="submit" value="注  册">
					</dd>
				</dl>
			</form>
		</div>
	</div>


</body>
</html>