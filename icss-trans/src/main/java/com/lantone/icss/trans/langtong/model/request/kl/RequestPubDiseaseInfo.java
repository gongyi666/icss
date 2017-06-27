package com.lantone.icss.trans.langtong.model.request.kl;

import java.io.Serializable;

public class RequestPubDiseaseInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String hospitalId;//医院编号
	private String pageNum;
	private String pageSize;
	
	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
