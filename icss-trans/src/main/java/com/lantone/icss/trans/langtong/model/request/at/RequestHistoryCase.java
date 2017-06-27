package com.lantone.icss.trans.langtong.model.request.at;

import java.io.Serializable;

public class RequestHistoryCase implements Serializable{

	private static final long serialVersionUID = 1L;

	private String patientId;
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
	private String pageNum;
	private String pageSize;
}
