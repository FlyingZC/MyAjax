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
	public List<City> queryAllCity() throws Exception
	{
		QueryRunner runner =new QueryRunner(DataSourceHelper.getSource());
		String sql=" select * from city";
		List<City> result = runner.query(sql,new BeanListHandler<City>(City.class));
		return result;
	}
}
