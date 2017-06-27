package com.lantone.icss.trans.langtong.model.response.kl;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

public class PageDiseaseInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String pageNum;
	private String pageSize;
	private List<HISPubDiseaseInfo> list = Lists.newArrayList();;
	private String size;
	private String orderBy;
	private String startRow;
	private String endRow;
	private String total;
	private String pages;
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public List<HISPubDiseaseInfo> getList() {
		return list;
	}
	public void setList(List<HISPubDiseaseInfo> list) {
		this.list = list;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getStartRow() {
		return startRow;
	}
	public void setStartRow(String startRow) {
		this.startRow = startRow;
	}
	public String getEndRow() {
		return endRow;
	}
	public void setEndRow(String endRow) {
		this.endRow = endRow;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
