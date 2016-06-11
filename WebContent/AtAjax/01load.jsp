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
	alert("heh");
	//
	var url="AtAjax/a.html";
	//发送时间戳.起到禁用缓存
	var mData={"data":new Date()};
	//load方法可以由任意html元素调用.直接插入到页面
	$("#btn").click(function(){
		$("#heh").load(url,mData);
		return false;
	});
})
 
	
</script>
</head>
<body>
	<a  id="btn" href="AtAjax/a.html">点我</a>
	<div id="heh"></div>
</body>
</html>