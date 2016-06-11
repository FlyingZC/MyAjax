package com.zc.entity;
//购物车中的每一项
public class ShoppingCarItem {
	//购买该数目的数量
	private int number;
	private int price;
	private String bookName;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
}
