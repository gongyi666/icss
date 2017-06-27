package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class ALLHistoryDetail implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;       //主键
	private String code;   //编码
	private String name;   //名称
	private String type;   //类型
	private String status; //状态
	private String remark; //备注
	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
