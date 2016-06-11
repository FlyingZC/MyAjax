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
		alert("heeh");
		$.ajaxSetup({
			type:"GET",
			url:"UserInfo.xml",
			dataType:"xml"
		});
		
	
		
		$("#btn1").click(function(){
			$.ajax({
				success:function(data){
					ShowData(data,"姓名","name");
				}
			});
			
		});
		
		$("#btn2").click(function(){
			$.ajax({
				success:function(data){
					ShowData(data,"性别","sex");
				}
			})
		});
		
		$("#btn3").click(function(){
			$.ajax({
				success:function(data){
					ShowData(data,"年龄","age");
				}
			})
		});
		
		 /*
         *根据名称与值，获取请求响应数据中的某部分
         *@Param d为请求响应后的数据
         *@Param n为数据中文说明字符
         *@Param d为数据在响应数据中的元素名称
         */
		function ShowData(d,n,v)
		{
			 $("#divTip").empty(); //先清空标记中的内容
             var strHTML = ""; //初始化保存内容变量
             $(d).find("User").each(function() { //遍历获取的数据
                 var $strUser = $(this);
                 strHTML += n + "：" + $strUser.find(v).text() + "<hr>";
             })
             $("#divTip").html(strHTML); //显示处理后的数据
		}
	})
</script>
</head>
<body>
<div><input type="button" value="姓名" id="btn1"/></div>
<div><input type="button" value="性别" id="btn2"/></div>
<div><input type="button" value="年龄" id="btn3"/></div>
<div id="tip"></div>
<div id="divTip"></div>
</body>
</html>