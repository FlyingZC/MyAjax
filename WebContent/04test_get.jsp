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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<base href="<%=basePath%>"/>
<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
<script type="text/javascript">
//如果使用$.get()$.get(url,args,function(data,textStatus){}提交表单.将表单中的action改为#,提交按钮为button
	$(function(){
			var url="testAjaxServlet";
		$("#tijiao").click(function(){
			alert(url);
			var args={"username":$("#username").val(),"password":$("#psw").val(),"method":"testGet"};
		$.get(url,args,function(data,textStatus){
			$("#resText").html(data);
		});	
		});
		
		$("#tijiao2").click(function(){
			var args={"username":$("#username").val(),"password":$("#psw").val(),"method":"testGetXml"};
			$.get(url,args,function(data,textStatus){
				//处理xml数据
				alert("heh");
				var username=$(data).find("username");
				var password=$(data).find("password");
				var txtHtml="<div>用户名为:"+username+"密码为:"+password+"</div>";
				$("#resText").html(txtHtml);
			},"xml");
		});
	})
</script>
<title>Insert title here</title>
</head>
<body>
	<form action="#" method="get">
		姓名:<input type="text" id="username" name="xingming"/><br/>
		密码:<input type="text" id="psw" name="mima"/><br />
		<input id="tijiao" type="button" value="提交返回html"/>
		<input id="tijiao2" type="button" value="提交返回xml"/>
	</form>
	<div id="resText"></div>
	
</body>
</html>