<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
<script type="text/javascript">
var jsonObj=
		{
			"name":"zcc",
			"age":12,
			"address":{"city":"BeiJing","school":"xzit"}
		};
	
	alert(jsonObj.name);
	alert(jsonObj.address.city);
		
	var jsonStr="{'name':'zzzz'}";
	//使用eval(会把这里的字符串作为js代码执行)方法
	var testStr="alert('hello eval')";
	eval(testStr);
	
	var testObject=eval("("+jsonStr+")");
	alert(testObject.name);
	
</script>

</head>
<body>

</body>
</html>