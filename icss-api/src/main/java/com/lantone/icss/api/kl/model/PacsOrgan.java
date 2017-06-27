package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class PacsOrgan implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;        //主键
	private String code;    //器官编码
	private String name;    //器官名称
	private String status;  //状态
	private String remark;  //备注
	private String direction;
	
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
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
}
