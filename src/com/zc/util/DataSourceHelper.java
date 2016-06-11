package com.zc.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DataSourceHelper {
	//私有化构造器.在外部只能过类的静态方法访问
	private DataSourceHelper(){}
    
	//通过JNDI获得DataSource
	public static DataSource getSource() throws Exception{ 
		//初始化上下文
		Context context = new InitialContext();
		//查找上下文环境中查找java组件(comp).
	    Context ctx = (Context)context.lookup("java:comp/env");
	    DataSource ds = (DataSource)ctx.lookup("jdbc/mysql");
	    //返回DataSource对象
		return ds;
   }
	
}
