package com.zc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.zc.entity.City;
import com.zc.entity.School;
import com.zc.util.DataSourceHelper;

public class SchoolDao {
	public SchoolDao(){}
	public List<Map<String,Object>> queryAllSchool(int cityId) throws Exception
	{
		QueryRunner runner =new QueryRunner(DataSourceHelper.getSource());
		String sql=" select schoolId,schoolName from school where cityId=? ";
		return runner.query(sql, new MapListHandler(),cityId);
		
	}

}
