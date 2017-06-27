package com.lantone.icss.api.at.model;

import java.io.Serializable;
import java.util.Date;

public class WaitingListIn implements Serializable{

	private static final long serialVersionUID = 1L;
	//机构代码
	private String hospitalId;
	//医生ID
	private String stffId;
	//科室ID
	private String departId;
	//0:待诊，1:诊中，2：完诊断，不传：全部
	private String regVisitedState; 
	//搜索：姓名、诊疗号、医疗卡号
	private String inputVal;
	//日期
	private Date regDate;
	
	
	
	
	
	
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getInputVal() {
		return inputVal;
	}
	public void setInputVal(String inputVal) {
		this.inputVal = inputVal;
	}
	public String getRegVisitedState() {
		return regVisitedState;
	}
	public void setRegVisitedState(String regVisitedState) {
		this.regVisitedState = regVisitedState;
	}
	public String getStffId() {
		return stffId;
	}
	public void setStffId(String stffId) {
		this.stffId = stffId;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	
}
