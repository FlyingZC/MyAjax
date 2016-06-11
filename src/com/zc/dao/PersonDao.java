package com.zc.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.naming.factory.DataSourceLinkFactory.DataSourceHandler;

import com.zc.util.DataSourceHelper;

public class PersonDao {
	public boolean changeSchool(int personId,int schoolId){
		String sql="update person set schoolId=? where personId=?";
		try {
			QueryRunner runner=new QueryRunner(DataSourceHelper.getSource());
			runner.update(sql,schoolId,personId);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
