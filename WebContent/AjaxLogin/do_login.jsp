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
    $(function() {
    	alert("hehe");
        $.ajax({ //请求登录页
            url: "AjaxLogin/login.html", //登录静态页
            dataType: "html",
            success: function(HTML) { //返回页面内容
            	alert("hehe");
                $("#frmUserLogin").html(HTML); //将页面内容置入表单
                $("#btnLogin").click(function() { //“登录”按钮单击事件
                    //获取用户名称
                    var strTxtName = encodeURI($("#txtName").val());
                    //获取输入密码
                    var strTxtPass = encodeURI($("#txtPass").val());
                    //开始发送数据
                    $.ajax({ //请求登录处理页.servlet
                        url: "loginServlet", //登录处理页
                        dataType: "html",
                        //传送请求数据
                        data: { txtName: strTxtName, txtPass: strTxtPass },
                        success: function(strValue) { //登录成功后返回的数据
                        	alert("haha");
                            //根据返回值进行状态显示
                            if (strValue == "True") {
                                $(".clsShow").html("操作提示，登录成功！");
                            }
                            else {
                                $("#divError").show().html("用户名或密码错误！");
                            }
                        }
                    })
                })
            }
        })
    })
</script>
</head>
<body>
<form id="frmUserLogin"></form>
</body>
</html>