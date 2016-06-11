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
<script type="text/javascript"></script>
<script type="text/javascript">
	window.onload=function()
	{
		var aNodes=document.getElementsByTagName("a");
		for(var i=0;i<aNodes.length;i++)
		{
			aNodes[i].onclick=function()
			{
				var request=new XMLHttpRequest();
				var url=this.href+"?time="+new Date();
				var method="GET";
				request.open(method, url);
				request.send(null);
				
				request.onreadystatechange=function()
				{
					if(request.readyState==4)
					{
						if(request.status==200||request.status==304)
						{
							document.getElementById("heh").innerHTML=request.responseText;
						}
					}
				}
				return false;	
			}
		}
	}
</script>
</head>
<body>
<ul>
	<li>
	<%
		out.println(basePath);
	%>
		<a href="<%=basePath%>t1.html">加载t1</a>
	</li>
	<li>
		<a href=""></a>
	</li>
	<li>
		<a href=""></a>
	</li>
</ul>
<div id="heh"></div>


</body>
</html>