package com.zc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestAjaxServlet
 */
@WebServlet(name = "testAjaxServlet", urlPatterns = { "/testAjaxServlet" })
public class TestAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method=request.getParameter("method");
		System.out.println(method);
		if(method.equals("testGet")){
			this.testGet(request,response);
		}else if(method.equals("testGetXml")){
			this.testGetXml(request,response);
		} 
	}
	
	protected void testGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("<div>欢迎您,"+username+"密码为:"+password+"</div>");
	}
	
	protected void testGetXml(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		response.setContentType("text/xml;charset=utf-8");
		//response.getWriter().write("<username>"+username+"</username><password>"+password+"</password>");
		response.getWriter().println("<?xml version='1.0' encoding='utf-8' ?><username>212</username><password>313</password>");
	}

}
