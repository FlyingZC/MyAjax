package com.zc.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.runners.Parameterized.Parameters;

import com.zc.util.DataSourceHelper;

//用来向数据库中插入随机数据
public class RandomInsertDao {
	/*
	 * int num:插入的条数,String tableName:表名
	 * */
	
	private int num;
	private String tableName;
	private String columnName1;
	private String columnName2;
	private String columnName3;
	private String columnName4;
	private String columnName5;
	
	private String randomName;
	
	public static void insertRandom(int num,String tableName) throws Exception{
		for(int i=0;i<num;i++){
			String sql="insert into ? set ?=?,?=?,?=?";
			QueryRunner runner=new QueryRunner(DataSourceHelper.getSource());
			
			
			runner.update(sql,tableName);
		}
	}
	
	public void setRandomName(String randomName){
		this.randomName=randomName;
	}
	
	public String getRandomName(){
		String name=null;
		String[] xing=new String[]{"张","王","李","赵","徐","陆","胡","孙"};
		String[] ming1=new String[]{"天","堂","和","时","发","到","分","点"};
		
		
		return name;
	}
}
