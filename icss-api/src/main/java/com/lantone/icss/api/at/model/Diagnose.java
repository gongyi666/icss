package com.lantone.icss.api.at.model;

import java.io.Serializable;

public class Diagnose implements Serializable{

	private static final long serialVersionUID = 1L;
	//就诊序号
	private String visId;
	//疾病序号
	private String disId;
	//ICD
	private String diaIcd;
	//诊断名称
	private String diaName;
	//诊断说明
	private String diaSummary;
	//诊断判别（确诊，疑诊）
	private String diaSign;
	//所属机构
	private String hospitalId;
	//发病时间
	private String diaTime;
	//ID
	private String id;
	public String getVisId() {
		return visId;
	}
	public void setVisId(String visId) {
		this.visId = visId;
	}
	public String getDisId() {
		return disId;
	}
	public void setDisId(String disId) {
		this.disId = disId;
	}
	public String getDiaIcd() {
		return diaIcd;
	}
	public void setDiaIcd(String diaIcd) {
		this.diaIcd = diaIcd;
	}
	public String getDiaName() {
		return diaName;
	}
	public void setDiaName(String diaName) {
		this.diaName = diaName;
	}
	public String getDiaSummary() {
		return diaSummary;
	}
	public void setDiaSummary(String diaSummary) {
		this.diaSummary = diaSummary;
	}
	public String getDiaSign() {
		return diaSign;
	}
	public void setDiaSign(String diaSign) {
		this.diaSign = diaSign;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getDiaTime() {
		return diaTime;
	}
	public void setDiaTime(String diaTime) {
		this.diaTime = diaTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}


