package com.zc.dao;
/*
 * BeanListHandler利用反射机制.Person类必须有一个空参的构造器.和一个对应参数的构造器.才能正确的赋值
 * */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zc.entity.Person;
import com.zc.util.DataSourceHelper;

public class SearchAllDao {
	public List<Person> searchAllPerson(int schoolId){
		List<Person> result=null;
		try {
			QueryRunner runner=new QueryRunner(DataSourceHelper.getSource());
			String sql="SELECT * FROM person WHERE schoolId=?";
		
			BeanListHandler<Person> handler=new BeanListHandler<Person>(Person.class);
			
			result = runner.query(sql,handler,schoolId);
			for(Person p:result){
				System.out.println(p.getPersonName()+p.getSchoolId());
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
