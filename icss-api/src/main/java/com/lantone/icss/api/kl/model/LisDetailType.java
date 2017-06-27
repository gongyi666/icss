package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class LisDetailType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;            //主键
	private Long standId;       //
	private String name;        //名称
	private String orderNo;     //排序号
	private String status;      //状态
	private String remark;      //备注
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStandId() {
		return standId;
	}
	public void setStandId(Long standId) {
		this.standId = standId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
