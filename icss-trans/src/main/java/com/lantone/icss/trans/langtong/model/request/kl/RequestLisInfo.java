package com.lantone.icss.trans.langtong.model.request.kl;

import java.io.Serializable;

public class RequestLisInfo implements Serializable{

private static final long serialVersionUID = 1L;
	
	private String binId;
	private String hospitalId;   //机构代码
	private String departId;  //科室ID
	private String stffId;   //医生ID
	private String chinaSpell;//拼音
	private String binType;//套餐类型
	
	
	public String getBinId() {
		return binId;
	}

	public void setBinId(String binId) {
		this.binId = binId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public String getStffId() {
		return stffId;
	}
	public void setStffId(String stffId) {
		this.stffId = stffId;
	}
	public String getChinaSpell() {
		return chinaSpell;
	}
	public void setChinaSpell(String chinaSpell) {
		this.chinaSpell = chinaSpell;
	}
	public String getBinType() {
		return binType;
	}
	public void setBinType(String binType) {
		this.binType = binType;
	}
	
}
