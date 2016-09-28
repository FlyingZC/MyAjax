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
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zc.entity.Person;
import com.zc.util.DataSourceHelper;
import com.zc.util.PageBean;

public class SearchAllDao {
	public PageBean<List<Person>> searchAllPerson(int schoolId,int currentPage,int pageSize){
		PageBean<List<Person>> pageBean=new PageBean<List<Person>>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageSize(pageSize);
		
		try {
			QueryRunner runner=new QueryRunner(DataSourceHelper.getSource());
			String sql1="select count(*) as totalRows from person  where schoolId=?";
			Long rows=runner.query(sql1,new ScalarHandler<Long>("totalRows"),schoolId);
			//totalRows可以计算出总页数
			pageBean.setTotalRows(rows.intValue());
			int start=(currentPage-1)*pageSize;
			String sql="SELECT * FROM person WHERE schoolId=? limit ?,?";//limit a,b起始位置.显示条数
			
			BeanListHandler<Person> handler=new BeanListHandler<Person>(Person.class);
			
			List<Person> _data = runner.query(sql,handler,schoolId,start,pageSize);
			for(Person p:_data){
				System.out.println("调用SearchAllDao--->"+p.getPersonName());
			}
			
			pageBean.setData(_data);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageBean;
	}
	
	public List<Person> searchAllNoPage(int schoolId){
		try {
			QueryRunner runner = new QueryRunner(DataSourceHelper.getSource());
			String sql="select * from person where schoolId=?";
			return runner.query(sql, new BeanListHandler<Person>(Person.class),schoolId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
