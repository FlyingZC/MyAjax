<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Insert title here</title>
		<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
		<script type="text/javascript">
			$(function() {
			var strTxtName;//输入框中的用户名
			//验证用户名是否可用
			$("#txtName").blur(function() {
				//获取用户名称
				strTxtName = encodeURI($("#txtName").val());
				var url="loginServlet";
				var args={"txtName":strTxtName,"op":"checkUser","time":new Date()};
				$.getJSON(url,args,function(result){
						if (result=="1") {
						$("#txtName").next("span").remove();
							$("#txtName").after("<span style='color:red'>您未注册,无法登录</span>");
						} else {
						$("#txtName").next("span").remove();
							$("#txtName").after("<span style='color:blue'>该用户名数据库中存在,可登录</span>");
						}
				});
			});
			
			$("#btnLogin").click(function() {
				//获取用户名称
				strTxtName = encodeURI($("#txtName").val());
				var strPass=encodeURI($("#txtPass").val());
				var url="loginServlet";
				var args={"txtName":strTxtName,"strPass":strPass,"op":"userLogin","time":new Date()};
				$.getJSON(url,args,function(result){
						if (result=="1") {
							alert("登录成功");
						} else {
							alert("登录失败");
						}
				});
			});
		});
		</script>
	</head>

	<body>
		<form id="frmUserLogin">
			<div class="divFrame">
				<div class="divTitle">
					<span>登录"AAA","BBB","CCC"已经被注册密码和用户名相同</span>
				</div>
				<div class="divContent">
					<div class="clsShow">
						<div id="divError" class="clsError"></div>
						<div>用户名<input id="txtName" type="text" class="txt" /></div>
						<div>密码<input id="txtPass" type="password" class="txt" /></div>
						<div>
							<input id="btnLogin" type="button" value="登录" class="btn" />&nbsp;&nbsp
							<input id="btnReset" type="reset" value="取消" class="btn" />
							<input id="test" type="button" value="点我" class="btn" />
						</div>
					</div>
				</div>
			</div>

		</form>
	</body>

</html>