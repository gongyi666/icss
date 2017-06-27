package com.lantone.icss.api.at.model;

import java.io.Serializable;

public class PatientWaitingInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//患者登记ID
	private Long id;
	//就诊人证号
	private String patCardNum;
	//就诊病人序号
	private String patId;
	//就诊病人姓名
	private String patName;
	//就诊人费用类别
	private String feeId;
	//就诊人费用性质
	private String natId;
	//就诊人性别
	private String patSex;
	//年龄
	private String patAge;
	//登记挂号就诊科室
	private String regDepartId;
	//登记挂号就诊医生ID
	private String regDoctorId;
	//就诊状态（0待接诊，1接诊中，2完成接诊）
	private String regVisitedState;
	//就诊类型（初复诊标识）
	private String regFirstSign;
	//门诊号(挂号序号)
	private String regNm;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPatCardNum() {
		return patCardNum;
	}
	public void setPatCardNum(String patCardNum) {
		this.patCardNum = patCardNum;
	}
	public String getPatId() {
		return patId;
	}
	public void setPatId(String patId) {
		this.patId = patId;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getFeeId() {
		return feeId;
	}
	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}
	public String getNatId() {
		return natId;
	}
	public void setNatId(String natId) {
		this.natId = natId;
	}
	public String getPatSex() {
		return patSex;
	}
	public void setPatSex(String patSex) {
		this.patSex = patSex;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getRegDepartId() {
		return regDepartId;
	}
	public void setRegDepartId(String regDepartId) {
		this.regDepartId = regDepartId;
	}
	public String getRegDoctorId() {
		return regDoctorId;
	}
	public void setRegDoctorId(String regDoctorId) {
		this.regDoctorId = regDoctorId;
	}
	public String getRegVisitedState() {
		return regVisitedState;
	}
	public void setRegVisitedState(String regVisitedState) {
		this.regVisitedState = regVisitedState;
	}
	public String getRegFirstSign() {
		return regFirstSign;
	}
	public void setRegFirstSign(String regFirstSign) {
		this.regFirstSign = regFirstSign;
	}
	public String getRegNm() {
		return regNm;
	}
	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}
	

}
