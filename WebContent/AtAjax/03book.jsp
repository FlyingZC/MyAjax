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
<!--  
	1.获取所有a节点.分别添加onclick函数.取消默认行为
	2.准备发送ajax请求.url(a节点的href).args(时间戳)
	3.响应结果为json对象.包括bookName,totalBookNumber,totalMoney
	4.把对应属性添加到页面
	
-->
<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("input[type='button']").click(function(){
			var url="<%=basePath%>addToCarServlet";
			
			var args={"bookName":($(this).attr("id")),"price":($(this).attr("price")),"date":new Date()};
			
			$.getJSON(url,args,function(data){
				$("#bookName").text(data.bookName);
				$("#totalBookNumber").text(data.totalBookNumber);
				$("#totalMoney").text(data.totalMoney);
			});
		});
	})
</script>

</head>
<body>
	您已经将&nbsp;<span id="bookName"></span>加入购物车.
	购物车中共有&nbsp;<span id="totalBookNumber"></span>本.
	合计&nbsp;<span id="totalMoney"></span>元
	
	Java<a href="addToCarServlet?bookName=java&price=100">加入购物车</a><br/>
	oracle<a href="addToCarServlet?bookName=oracle&price=200">加入购物车</a><br/>
	android<a href="addToCarServlet?bookName=android&price=300">加入购物车</a><br/>
	
	<input type="button" id="java" price="100" value="购买java"/>
	<input type="button" id="oracle" price="200" value="购买oracle"/>
	<input type="button" id="android" price="300" value="购买android"/>
	
</body>
</html>