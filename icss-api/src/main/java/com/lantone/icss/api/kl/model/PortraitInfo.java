package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class PortraitInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;             //主键
	private Long parentId;       //上级Id
	private Long transverseId;   //横向菜单id
	private String name;         //名称
	private String type;         //类型
	private String endLevel;     //是否为竖向菜单末级
	private String orderNo;      //排序号
	private String status;       //状态
	private String remark;       //备注
	private String groupType;
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getTransverseId() {
		return transverseId;
	}
	public void setTransverseId(Long transverseId) {
		this.transverseId = transverseId;
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
	public String getEndLevel() {
		return endLevel;
	}
	public void setEndLevel(String endLevel) {
		this.endLevel = endLevel;
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
