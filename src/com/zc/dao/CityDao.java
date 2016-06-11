package com.zc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;

import com.zc.entity.City;
import com.zc.util.DataSourceHelper;


public class CityDao {
	@Test
	public List<Map<String,Object>> queryAllCity() throws Exception
	{
		List<City> citys=new ArrayList<City>();
		QueryRunner runner =new QueryRunner(DataSourceHelper.getSource());
		String sql=" select * from city";
		List<Map<String,Object>> hehe=runner.query(sql, new MapListHandler());
		return hehe;
//		for(Map<String,Object> map:hehe)
//		{
//			System.out.println("List------"+map.get("city_id"));
//		}
//		
//		 citys=runner.query(sql, new BeanListHandler<City>(City.class));
		//return runner.query(sql, new BeanListHandler<City>(City.class));
//		for(City city:citys)
//		{
//			System.out.println("dao"+city.getCityId()+city.getCityName());
//		}
//		return citys;
	}

}
