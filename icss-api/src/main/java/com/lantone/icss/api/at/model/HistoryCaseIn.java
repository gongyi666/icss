package com.lantone.icss.api.at.model;

import java.io.Serializable;

public class HistoryCaseIn implements Serializable{

	private static final long serialVersionUID = 1L;
	//患者id
	private String patientId;
	//页数（第几页）
	private String pageNum;
	//每页条数
	private String pageSize;
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
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
	
	
}
