package com.zc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.zc.dao.CityDao;
import com.zc.dao.PersonDao;
import com.zc.dao.SchoolDao;
import com.zc.dao.SearchAllDao;
import com.zc.entity.City;
import com.zc.entity.Person;
import com.zc.entity.School;
import com.zc.util.PageBean;

public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method=request.getParameter("method");
		System.out.println("---------方法"+method);
		if("getCity".equals(method))
		{
			try {
				this.getCity(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if("getSchool".equals(method))
		{
			try {
				this.getSchool(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if("searchAll".equals(method)){
			try {
				this.dealSearchAll(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("changeSchool".equals(method)){
			try {
				this.dealChangeSchool(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("searchAllNoPage".equals(method)){
			try {
				this.dealSearchAllNoPage(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("getCityByMobile".equals(method)){
			try {
				this.getCityByMobile(request, response);
			} catch (Exception e) {
			}
		}
	}
	
	protected void getCity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		CityDao dao=new CityDao();
		 List<City> citys = dao.queryAllCity();
		System.out.println(citys);
		ObjectMapper mapper=new ObjectMapper();
		//3.调用mapper的writeValueAsString()方法把一个对象转为一个json字符串
		String jsonCitys=mapper.writeValueAsString(citys);
		System.out.println(jsonCitys);
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonCitys.toString());
	}
	
	//测试手机
	protected void getCityByMobile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		CityDao dao=new CityDao();
		 List<City> citys = dao.queryAllCity();
		System.out.println(citys);
		ObjectMapper mapper=new ObjectMapper();
		//3.调用mapper的writeValueAsString()方法把一个对象转为一个json字符串
		String jsonCitys=mapper.writeValueAsString(citys);
		System.out.println(jsonCitys);
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getOutputStream().write(jsonCitys.toString().getBytes("utf-8"));
	}
	
	
	protected void getSchool(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cityId=(request.getParameter("cityId"));
		int city=Integer.parseInt(cityId);
		System.out.println("fangfagetSchool");
		SchoolDao dao=new SchoolDao();
		System.out.println("getquery.....School23");
		
		List<Map<String,Object>> schools=dao.queryAllSchool(city);
		
		List<School> mSchools=new ArrayList<School>();
		for(Map<String,Object> map:schools)
		{
			School school=new School();
			//map.get("key");
			school.setSchoolId((Integer) map.get("schoolId"));
			school.setSchoolName((String) map.get("schoolName"));
			mSchools.add(school);
		}
		
		ObjectMapper mapper=new ObjectMapper();
		//3.调用mapper的writeValueAsString()方法把一个对象转为一个json字符串
		String jsonSchools=mapper.writeValueAsString(mSchools);
		System.out.println(jsonSchools);
		
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonSchools.toString());
		
		System.out.println("查询wanbi///");	
	}
	
	protected void dealSearchAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String _cityId = request.getParameter("cityId");
		Integer cityId=Integer.parseInt(_cityId);
		System.out.println("dealSearchAll"+"-->"+cityId);
		String _schoolId=request.getParameter("schoolId");
		Integer schoolId=Integer.parseInt(_schoolId);
		
		String _currentPage=request.getParameter("currentPage");
		String _pageSize=request.getParameter("pageSize");
		int currentPage=1;
		int pageSize=10;
		
		if(_currentPage!=null)
		{currentPage=Integer.parseInt(_currentPage);}
		if(_pageSize!=null)
		{pageSize=Integer.parseInt(_pageSize);}
		
		
		SearchAllDao dao=new SearchAllDao();
		System.out.println("调用dao");
		PageBean<List<Person>> persons=dao.searchAllPerson(schoolId,currentPage,pageSize);
		System.out.println(persons);
		ObjectMapper mapper=new ObjectMapper();
		String jsonPersons = mapper.writeValueAsString(persons);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(jsonPersons);
		
		System.out.println("调用dao后"+jsonPersons);
	}

	protected void dealChangeSchool(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String _personId = request.getParameter("personId");
		String _schoolId = request.getParameter("schoolId");
		Integer personId=Integer.parseInt(_personId);
		Integer schoolId=Integer.parseInt(_schoolId);
		System.out.println("dealChangeSchool--schoolId"+schoolId+"personId"+personId);
		PersonDao dao=new PersonDao();
		boolean result=dao.changeSchool(personId, schoolId);
		System.out.println("dealChangeSchool-->"+result);
		response.setContentType("application/json;charset=UTF-8");
		//json字符串必须{"a":"b","c":"d"}转义字符吧
		System.out.println("{\"result\":\""+result+"\"}");
		response.getWriter().print("{\"result\":\""+result+"\"}");
	}
	
	protected void dealSearchAllNoPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String _schoolId=request.getParameter("schoolId");
		int schoolId = Integer.parseInt(_schoolId);
		SearchAllDao dao=new SearchAllDao();
		List<Person> persons = dao.searchAllNoPage(schoolId);
		ObjectMapper mapper=new ObjectMapper();
		String jsonPersons = mapper.writeValueAsString(persons);
		System.out.println("调用dealSearchAllNoPage后"+jsonPersons);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(jsonPersons);
	}
}
