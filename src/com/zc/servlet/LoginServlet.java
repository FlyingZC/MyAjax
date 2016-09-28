package com.zc.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<String> userNames=Arrays.asList("AAA","BBB","CCC");
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("op");
		String name=request.getParameter("txtName");
		if("checkUser".equals(method)){//判断用户名是否可用
			this.userValid(request, response);
		}
		if("userLogin".equals(method)){//登录,判断用户名和密码是否匹配
			this.userLogin(request,response);
		}
		if("userValidHtm".equals(method)){//登录,判断用户名和密码是否匹配
			this.userValidHtm(request,response);
		}
	}
	
	private void userLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String name=request.getParameter("txtName");
		String psw=request.getParameter("strPass");
		//简单认为密码和用户名相同
		String msg=null;
		if(userNames.contains(name)&&psw.equals(name)){
			msg="1";
		}else{
			msg="0";
		}
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(msg);
	}

	protected void userValid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//http://localhost:8080/MyAjax/loginServlet?op=checkUser&textName=AAA
		String name=request.getParameter("txtName");
		String msg=null;
		if(userNames.contains(name)){
			msg="0";//用户名不可用(已经被注册)
		}
		else{
			msg="1";//用户名可用(未被注册)
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(msg);
	}
	//返回html
	protected void userValidHtm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//http://localhost:8080/MyAjax/loginServlet?op=checkUser&textName=AAA
		String name=request.getParameter("txtName");
		String msg=null;
		if(userNames.contains(name)){
			msg="<span>用户名可用</span>";
		}
		else{
			msg="<span>用户名不可用</span>";//用户名可用(未被注册)
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(msg);
	}
}
