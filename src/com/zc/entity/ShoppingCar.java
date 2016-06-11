package com.zc.entity;

import java.util.HashMap;
import java.util.Map;


//购物车
public class ShoppingCar {
	
	
	private int totalNum;
	//键是书名.
	private Map<String,ShoppingCarItem> items=new HashMap<String,ShoppingCarItem>();
	
	//把书放入购物车
	public void addToCar(String bookName,int price)
	{
		//如果购物车中已经有该书.直接书数量加1
		if(items.containsKey(bookName))
		{
			ShoppingCarItem item=items.get(bookName);
			item.setNumber(item.getNumber()+1);
		}
		else
		{
			ShoppingCarItem item=new ShoppingCarItem();
			item.setBookName(bookName);
			item.setPrice(price);
			item.setNumber(1);
			items.put(bookName,item);
		}
	}
	
	//获取购买所有书的总数
	public int getTotalBookNumber(){
		int total=0;
		//遍历value集
		for(ShoppingCarItem item:items.values()){
			total+=item.getNumber();
		}
		return total;
	}
	
	public int getTotalMoney(){
		int money=0;
		for(ShoppingCarItem item:items.values()){
			money+=item.getNumber()*item.getPrice();
		}
		return money;
	}
	
}
