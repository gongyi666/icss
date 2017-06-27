package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class ApparatusType implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id; 
	private String name;
	private String status;
	private String remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	
	
}
