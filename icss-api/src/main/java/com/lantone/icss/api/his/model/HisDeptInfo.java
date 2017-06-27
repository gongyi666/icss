package com.lantone.icss.api.his.model;

import java.io.Serializable;

public class HisDeptInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String deptCode;      //部门编码
	private String parentCode;    //上级部门编码
	private String hospitalCode;  //医院编码
	private String deptName;      //部门名称
	private String status;        //状态
	
	
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
