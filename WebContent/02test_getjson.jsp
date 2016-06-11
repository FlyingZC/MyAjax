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
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#btn").click(function(){
			alert("hehee");
			$.getJSON("<%=basePath%>UserInfo.json",function(data){
				$("#heh").empty();
				alert(data);
				var strHtml="";
				$.each(data,function(infoIndex,info){
					strHtml+="姓名:"+info["name"]+"<br/>";
					strHtml+="性别"+info["sex"]+"<br/>";
					strHtml+="邮箱"+info["email"]+"<br/>";
				});
				$("#heh").html(strHtml);
			});
		});
	})
</script>
</head>
<body>
	<input type="button" id="btn" value="点我"/>
	<div id="heh">呵呵</div>
</body>
</html>