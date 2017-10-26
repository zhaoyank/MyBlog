package com.kaishengit.util;

import java.util.List;

public class Page<T> {

	// 总页数
	private int pageTotal;
	// 第pageNo页
	private int pageNo;
	// T的总数量
	private int countOfT;
	// 每页显示5行数据
	private int pageSize = 5;
	// 每页的数据集合
	private List<T> items;
	// 每页开始的数据id
	private int startNo;

	/**
	 * 构造方法 获得startNo,pageTotal,
	 * 
	 * @param pageNo
	 * @param countOfT
	 */
	public Page(int pageNo, int countOfT) {
		this.countOfT = countOfT;

		pageTotal = countOfT / pageSize;
		if (countOfT % pageSize != 0) {
			pageTotal++;
		}
		
		if (pageNo >= pageTotal) {
			pageNo = pageTotal;
		}

		if (pageNo <= 1) {
			pageNo = 1;
		}

		this.pageNo = pageNo;
		startNo = (pageNo - 1) * pageSize;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getCountOfT() {
		return countOfT;
	}

	public void setCountOfT(int countOfT) {
		this.countOfT = countOfT;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

}
