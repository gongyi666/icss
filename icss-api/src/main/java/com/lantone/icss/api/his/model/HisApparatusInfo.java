package com.lantone.icss.api.his.model;

import java.io.Serializable;

public class HisApparatusInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String apparatusCode;    //his器械编码	
	private String hospitalCode;     //医院编码
	private String apparatusName;    //器械名称
	private String remark;           //备注
	
	
	public String getApparatusCode() {
		return apparatusCode;
	}
	public void setApparatusCode(String apparatusCode) {
		this.apparatusCode = apparatusCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getApparatusName() {
		return apparatusName;
	}
	public void setApparatusName(String apparatusName) {
		this.apparatusName = apparatusName;
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
