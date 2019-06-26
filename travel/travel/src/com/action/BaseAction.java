package com.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * action基类 分页参数
 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport{
	
	protected int page = 1;	// 当前页数
	protected int pages = 1;	// 总页数
	protected int size = 5;	// 每页记录数
	
	protected int getPages(int total, int size) {
		return total%size==0 ? total/size : total/size+1;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

}
