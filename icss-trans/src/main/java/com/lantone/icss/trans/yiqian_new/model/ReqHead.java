package com.lantone.icss.trans.yiqian_new.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "head")
public class ReqHead {
	@XmlElement(name = "tranKey")
	private String tranKey = "";
	@XmlElement(name = "tranType")
	private String tranType = "";
	@XmlElement(name = "stffNo")
	private String stffNo = "";
	@XmlElement(name = "hospitalId")
	private String hospitalId = "";
	@XmlElement(name = "departId")
	private String departId = "";
	@XmlElement(name = "tranData")
	private String tranData = "";
	public String getTranKey() {
		return tranKey;
	}
	public void setTranKey(String tranKey) {
		this.tranKey = tranKey;
	}
	public String getTranType() {
		return tranType;
	}
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}
	public String getStffNo() {
		return stffNo;
	}
	public void setStffNo(String stffNo) {
		this.stffNo = stffNo;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getTranData() {
		return tranData;
	}
	public void setTranData(String tranData) {
		this.tranData = tranData;
	}
	
}
