package com.lantone.icss.trans.langtong.model.response.login;

import java.io.Serializable;

public class HISLogin implements Serializable{

	private static final long serialVersionUID = 1L;
	//医院编号
	private String hospitalId;
	//
	private String depId;
	private String id;
	private String sffName;
	private String sffSex;
	private String sffCardType;
	private String sffCardInfo;
	private String sffProfessional;
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSffName() {
		return sffName;
	}
	public void setSffName(String sffName) {
		this.sffName = sffName;
	}
	public String getSffSex() {
		return sffSex;
	}
	public void setSffSex(String sffSex) {
		this.sffSex = sffSex;
	}
	public String getSffCardType() {
		return sffCardType;
	}
	public void setSffCardType(String sffCardType) {
		this.sffCardType = sffCardType;
	}
	public String getSffCardInfo() {
		return sffCardInfo;
	}
	public void setSffCardInfo(String sffCardInfo) {
		this.sffCardInfo = sffCardInfo;
	}
	public String getSffProfessional() {
		return sffProfessional;
	}
	public void setSffProfessional(String sffProfessional) {
		this.sffProfessional = sffProfessional;
	}
	
	
}
