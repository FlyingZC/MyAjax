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
							//1.结果为json格式.所以需要使用responseText来获取
							var result=request.responseText;
							//将json转换为js对象
							var obj=eval("("+result+")");
							//2.结果不能直接使用.先创建对应的节点.再把节点节点放到网页中
							//目标是转成以下格式
							//2.1先读取json中的数据
							var name=obj.person.name;
							var age=obj.person.school;
							var school=obj.person.age;
							alert(name);
							alert(age);
							alert(school);
							//创建一个<div>里面放name</div>
							var dNode=document.createElement("div");
							dNode.appendChild(document.createTextNode(name));
							//将上面的<div>放到..
							document.getElementById("heh").appendChild(dNode);
							
							
							var dNode2=document.createElement("div");
							dNode2.appendChild(document.createTextNode(age));
							document.getElementById("heh").appendChild(dNode2);
							
							var dNode3=document.createElement("div");
							dNode3.appendChild(document.createTextNode(school));
							document.getElementById("heh").appendChild(dNode3);
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
		<a href="<%=basePath%>t3.js">加载t1</a>
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