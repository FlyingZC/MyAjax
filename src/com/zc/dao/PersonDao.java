package com.zc.dao;

import org.apache.commons.dbutils.QueryRunner;
import com.zc.util.DataSourceHelper;

public class PersonDao {
	public boolean changeSchool(int personId,int schoolId){
		String sql="update person set schoolId=? where personId=?";
		try {
			QueryRunner runner=new QueryRunner(DataSourceHelper.getSource());
			runner.update(sql,schoolId,personId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
