<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		//$("#city option:not(:first)").remove();
		
		//获取下拉框中的city.刚进入页面就获取city信息.这样点下拉框就能显示出来
		var url="<%=basePath%>cityServlet";
		var args={"time":new Date(),"method":"getCity"};
		//返回的Json[{city_id=1, city_name=徐州}, {city_id=2, city_name=南京}, {city_id=3, city_name=北京}]
		$.getJSON(url,args,function(data){
			if(data.length==0){
				alert("无城市");
			}else{
				for(var i=0;i<data.length;i++){
					var cityId=data[i].cityId;
					var cityName=data[i].cityName;
					$("#city").append("<option value='"+ cityId+"'> "+ cityName+   "</option>");
				}
			}
			
		});	
		//当city改变时.自动加载school下拉框信息
		$("#city").change(function(){
			//在上面添加节点时.把数据库中获取的id值作为value
			var cityId=$(this).val();
			$("#school option:not(:first)").remove();
			var url="<%=basePath%>cityServlet";
			var args={"time":new Date(),"method":"getSchool","cityId":cityId};
			$.getJSON(url,args,function(data){
				if(data.length==0)
					{alert("当前城市没有学校");}
				else
				{
					for(var i=0;i<data.length;i++){
						var schoolId=data[i].schoolId;
						var schoolName=data[i].schoolName;
						$("#school").append("<option value='"+ schoolId+"'> "+ schoolName+   "</option>");
					}
					
				}
			});
		});
		
		
		$("#btn_searchAll").click(function(){
			if($("#city").val()==""){
				alert("请先选择具体城市再搜索");
			}
			else if($("#school").val()==""){
				alert("请先选择具体学校再搜索");
			}
			else{
				var url="<%=basePath%>cityServlet";
				var cityId=$("#city").val();
				var schoolId=$("#school").val();
				var args={"time":new Date(),"method":"searchAll","cityId":cityId,"schoolId":schoolId};
				$.getJSON(url,args,function(data){
					//[{"personId":8,"personName":"qw","personAge":3,"schoolId":2},{"personId":9,"personName":"rtt","personAge":34,"schoolId":2},{"personId":10,"personName":"ada","personAge":21,"schoolId":2},{"personId":11,"personName":"wqe","personAge":12,"schoolId":2},{"personId":12,"personName":"werr","personAge":21,"schoolId":2}]

					for(var i=0;i<data.length;i++){
						var personId=data[i].personId;
						var personName=data[i].personName;
						var personAge=data[i].personAge;
						var schoolId=data[i].schoolId;
		
						$("#stu").append("<tr id='hang"+personId+"'><td>"+personId+"</td><td>"+personName+"</td><td>"+personAge+"</td><td>"+schoolId +"<td></tr>");
						if(personId==1){
							$("#hang"+personId).append("<td>分配学校</td>");
						}else{
							$("#hang"+personId).append("<td>调换学校 撤销学校</td>");
						}
					}
				});
			}
			
		});
	})
</script>
</head>
<body>
<!-- 只要访问该页面.即会把请求forward到cityServlet.但是页面不 -->


	city:
	<select id="city">
		<option value="">请选择...</option>	
	</select>

	<!-- 二级初始无数据 -->
	school:
	<select id="school">
		<option value="">请选择...</option>
	</select>
	
	<!-- 搜索 -->
	<input type="button" value="搜索" id="btn_searchAll"/>
	<!-- 表格初始隐藏 -->
	<table id="stu" border="1" cellspacing="0" cellpadding="5">
		<tr></tr>
	</table>
</body>
</html>