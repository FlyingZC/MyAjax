package com.zc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zc.entity.ShoppingCar;

public class AddToCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取请求参数id,price
		String bookName=request.getParameter("bookName");
		System.out.println(bookName);
		
		String me=request.getParameter("method");
		System.out.println(me);
		
		Integer price=Integer.parseInt(request.getParameter("price"));
		//2.获取购物车对象ShoppingCar.在Session范围内
		HttpSession session=request.getSession();
		ShoppingCar shoppingCar=(ShoppingCar) session.getAttribute("shoppingCar");
		if(shoppingCar==null)
		{
			shoppingCar=new ShoppingCar();
			session.setAttribute("shoppingCar",shoppingCar);
			
		}
		//3.把点击的选项加入shoppingCar.调用ShoppingCar类中的addToCar(bookName,price)方法
		shoppingCar.addToCar(bookName, price);
		
		//准备响应结果的json对象
		//   {"bookName":"a","totalBookNumber":"1","totalMoney":"11"}
		//ctrl+f:转义/*
		
		StringBuilder result=new StringBuilder();
		result.append("{").append("\"bookName\":"+"\""+bookName+"\"")
		.append(",")
		.append("\"totalBookNumber\":"+"\""+shoppingCar.getTotalBookNumber()+"\"")
		.append(",")
		.append("\"totalMoney\":"+"\""+shoppingCar.getTotalMoney()+"\"")
		.append("}");
		
		System.out.println(result);
		//响应json对象
		//json是js格式
		//response.setContentType("text/html");
		
		
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result.toString());
		
		
		
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json; charset=utf-8");
//		String jsonStr = "{\"bookName\":\"AAA\",\"totalBookNumber\":\"33\",\"totalMoney\":\"32232\"}";
//		System.out.println(jsonStr);
//		PrintWriter out = null;
//		try {
//		    out = response.getWriter();
//		    out.write(jsonStr);
//		} catch (IOException e) {
//		    e.printStackTrace();
//		} finally {
//		    if (out != null) {
//		        out.close();
//		    }
//		}
		
		
		
	}

}
