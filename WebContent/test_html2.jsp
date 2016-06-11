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
<title>Insert title here</title>
<script type="text/javascript">
	window.onload=function(){
		//1.获取a节点.为其添加Onclick响应函数
		document.getElementsByTagName("a")[0].onclick=function(){
		//3.创建一个XMLHttpRequest对象
		var request=new XMLHttpRequest();
		//4.准备发送请求的数据:url.加上时间戳.可以起到禁用缓存的作用
		var url=this.href+"?time="+new Date();
		var method="GET";
		//5.调用XMLHttpRequest对象的open方法.open方法建立对服务器的调用
		request.open(method,url);
		//6.调用XMLHttpRequest对象的send方法.send方法向服务器发送请求
		request.send(null);	
		//7.为XMLHttpRequest对象添加onreadystatechange响应函数.每个状态发生改变时都会触发该事件处理器.由服务器触发
		request.onreadystatechange=function(){
			//readyState:请求的状态.0未初始化.1正在加载.2已经加载.3交互中.4完成
		//8.判断响应是否完成:XMLHttpRequest对象的readyState属性值为4的时候
			if(request.readyState==4){
		//9.再判断响应是否可用:XMLHttpRequest对象status的属性值为200.服务器的http状态码
		//404 not found:未找到页面.(可能不存在.路径错误等)
		//403 forbidden:禁止访问
		//500 internal service error 内部服务器出错
		//200 ok:一切正常
		//304 not modified:没有被修改
				if(request.status==200||request.status==304){
		//10.打印响应结果:responseText.responseText:服务器的响应.表示为一个串
					document.getElementById("heh").innerHTML=request.responseText;	
					alert(request.responseText);
				}
			}
		}
			//2.取消a节点的默认行为
			return false;
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
<div id="heh">多多多</div>

</body>
</html>