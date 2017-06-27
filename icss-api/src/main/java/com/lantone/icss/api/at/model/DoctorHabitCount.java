package com.lantone.icss.api.at.model;

import java.io.Serializable;

public class DoctorHabitCount implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;           //主键
	private Long doctorId;     //医生id
	private String habitId;    //习惯使用的对象id
	private Long countNum;     //计数
	private String type;       //类型（1：主症状，2：非主症状，3：既往史，4：家族史）
	private String remark;     //备注
	private String name;       //名称
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public String getHabitId() {
		return habitId;
	}
	public void setHabitId(String habitId) {
		this.habitId = habitId;
	}
	public Long getCountNum() {
		return countNum;
	}
	public void setCountNum(Long countNum) {
		this.countNum = countNum;
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
	
}
