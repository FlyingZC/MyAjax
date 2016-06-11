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
		//$(":input[name='username']").(function(){
		//	var val=$(this).val();
		//	if(val=$(this).defaultValue){
		//		$(this).val("");
		//	}
	//	}).
		
		$(":input[name='username']").change(function(){
			var val=$(this).val();
			val=$.trim(val);
			if(val !=""){
				var url="<%=basePath%>loginServlet";
				var args={"username":val,"time":new Date(),"action":"checkUser"};
				$.post(url,args,function(data){		
					alert(args.username);
					$("#message").html(data);
				});
			}
				
			
		});
	})
</script>
</head>
<body>
<form>
	姓名:<input type="text" name="username"/>
	<span id="message"></span>
	<br/>
	密码:<input type="password"/><br/>
	<input type="submit" value="登录"/>
	<input type="reset"/>
</form>
	
	

</body>
</html>