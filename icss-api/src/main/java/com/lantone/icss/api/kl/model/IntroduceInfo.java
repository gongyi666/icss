package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class IntroduceInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id; 			// 主键
	private String code; 		// 编码
	private String shortName;   //简称
	private String description; // 描述
	private String nice; 		// 描述
	private String status; 		// 状态
	private String remark; 		// 备注
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNice() {
		return nice;
	}
	public void setNice(String nice) {
		this.nice = nice;
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
