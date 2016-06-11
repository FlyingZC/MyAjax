package com.zc.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.zc.entity.Customer;

public class TestJackson {
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException
	{
		//1.导入jar包
		//2.创建ObjectMapper对象
		ObjectMapper mapper=new ObjectMapper();
		//3.调用mapper的writeValueAsString()方法把一个对象转为一个json字符串
		Customer customer=new Customer("zz",11,"男");
		String jsonStr=mapper.writeValueAsString(customer);
		System.out.println(jsonStr);
		//4.注意:JackSon使用getter方法来定位json对象的属性
		//5.可以通过添加注解org.codehaus.jackson.annotate.JsonIgnore来忽略一个getter定义的属性
		
		//还可以定义json数组
		// [{"name":"zz","age":11,"sex":"男"},{"name":"zzx","age":121,"sex":"女"}]
		List<Customer> customers=Arrays.asList(new Customer("zzc",12,"女"),new Customer("zzx",121,"女"));
		System.out.println(customers);
		String jsonStr2=mapper.writeValueAsString(customers);
		System.out.println(jsonStr2);
		
		
		
		
	}

}
