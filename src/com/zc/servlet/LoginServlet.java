package com.zc.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "loginServlet", urlPatterns = { "/loginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String name=request.getParameter("strTxtName");
//		name=java.net.URLDecoder.decode(name);
//		String psw=request.getParameter("strTxtPass");
//		psw=java.net.URLDecoder.decode(psw);
//		if("zc".equals(name)&&"1262".equals(psw))
//		{
//			request.setAttribute("strValue","True");
//			request.getRequestDispatcher("AjaxLogin/do_login.jsp").forward(request, response);
//		}
		
	}
		
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		if("checkUser".equals(action))
		{this.userValid(request, response);}
	}
	
	protected void userValid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> userNames=Arrays.asList("AAA","BBB","CCC");
		String name=request.getParameter("username");
		System.out.println(name);
		String msg;
		System.out.println("....");
		if(userNames.contains(name))
		{
			msg="<font color='red'>该用户名已经被使用</font>";
			System.out.println("hehe");
		}
		else{
			msg="<font color='green'>该用户名可用</font>";
			System.out.println("haha");
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(msg);
	}

}
