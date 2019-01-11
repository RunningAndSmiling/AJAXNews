package entity;

import java.util.List;

public class Page<T> {
	private int totalRecords;	// 总记录数
	private int pageNum;	// 当前页码
	private int pageSize;	//每页显示的记录数
	private int totalPages;	//总页数
	private List<T> ls;
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<T> getLs() {
		return ls;
	}
	public void setLs(List<T> ls) {
		this.ls = ls;
	}
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Page(int totalRecords, int pageNum, int pageSize, List<T> ls) {
		super();
		this.totalRecords = totalRecords;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.ls = ls;
		if(totalRecords % pageSize==0){
			this.totalPages = totalRecords/pageSize;// 总记录数  %  每页显示的数据数量  
		}else{
			this.totalPages = (totalRecords/pageSize)+1;
		}
	}
}
