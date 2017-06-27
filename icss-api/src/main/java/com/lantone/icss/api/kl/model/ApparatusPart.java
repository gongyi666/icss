package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class ApparatusPart implements Serializable{

	private static final long serialVersionUID = 1L;

	private String apparatusCode; //器械编码
	private String partCode;  //部位编码
	private String organCode; //器官编码
	
	public String getApparatusCode() {
		return apparatusCode;
	}
	public void setApparatusCode(String apparatusCode) {
		this.apparatusCode = apparatusCode;
	}
	public String getPartCode() {
		return partCode;
	}
	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}
	public String getOrganCode() {
		return organCode;
	}
	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
