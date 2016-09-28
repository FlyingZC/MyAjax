package com.zc.util;


public class PageBean<T> {
	private int currentPage;
	private int pageSize;
	private int totalPages;
	private int totalRows;
	private T data;
	public PageBean() {
		super();
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	//
	public int getTotalPages() {
		return totalRows%pageSize==0?totalRows/pageSize:(totalRows/pageSize+1);
	}
	
	
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
	
	
}
