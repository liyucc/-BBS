package com.congbbs.javabean;


public class PageBean {
	private int currPage; //当前页数
	private int pageSize; //当前显示的记录数
	private int totalCount; //总记录数
	private int tatalPage;  //总页数
	
	public PageBean(){		//无参构造方法
		
	}
	
	public PageBean(int currPage, int pageSize, 
			int totalCount, int tatalPage) {  //有参构造方法
		this.currPage = currPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.tatalPage = tatalPage;
	}
    //相应的set和get方法
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTatalPage() {
		return tatalPage;
	}
	public void setTatalPage(int tatalPage) {
		this.tatalPage = tatalPage;
	}
}
