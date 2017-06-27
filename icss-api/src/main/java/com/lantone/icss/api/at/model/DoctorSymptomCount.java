package com.lantone.icss.api.at.model;

import java.io.Serializable;

/**
 * @Description:医生症状使用记录
 * @author : luwg
 * @time : 2016年12月28日 上午10:00:49
 * 
 */
public class DoctorSymptomCount implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;           //主键
	private Long doctorId;     //医生id
	private Long symptomId;    //症状id
	private Long countNum;     //计数
	private String remark;     //备注
	
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
	public Long getSymptomId() {
		return symptomId;
	}
	public void setSymptomId(Long symptomId) {
		this.symptomId = symptomId;
	}
	public Long getCountNum() {
		return countNum;
	}
	public void setCountNum(Long countNum) {
		this.countNum = countNum;
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
