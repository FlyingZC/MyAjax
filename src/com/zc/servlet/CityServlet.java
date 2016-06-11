package com.zc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

/**
 * Servlet implementation class CityServlet
 */
@WebServlet(name = "cityServlet", urlPatterns = { "/cityServlet" })
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if("searchAll".equals(method)){
			try {
				this.dealSearchAll(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("changeSchool".equals(method)){
			try {
				this.dealChangeSchool(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected void getCity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		CityDao dao=new CityDao();
		List<Map<String,Object>> citys=dao.queryAllCity();
		System.out.println(citys);
		
		
		List<City> mCitys=new ArrayList<City>();
		for(Map<String,Object> map:citys)
		{
			City city=new City();
			city.setCityId((Integer) map.get("city_id"));
			city.setCityName((String) map.get("city_name"));
			mCitys.add(city);
		}
		
		
		
		ObjectMapper mapper=new ObjectMapper();
		//3.调用mapper的writeValueAsString()方法把一个对象转为一个json字符串
		String jsonCitys=mapper.writeValueAsString(mCitys);
		System.out.println(jsonCitys);
		
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonCitys.toString());
		
		
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
		SearchAllDao dao=new SearchAllDao();
		System.out.println("调用dao");
		List<Person> persons=dao.searchAllPerson(schoolId);
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
}
