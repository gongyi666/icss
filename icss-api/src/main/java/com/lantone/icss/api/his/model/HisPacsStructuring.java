package com.lantone.icss.api.his.model;

import java.io.Serializable;

public class HisPacsStructuring implements Serializable{

	private static final long serialVersionUID = 1L;

	private String hisPartCode; //his部位编码
	
	private String hisApparatusCode;  //his器械编码
	
	private String structuringName;  //结构化名称

	public String getHisPartCode() {
		return hisPartCode;
	}

	public void setHisPartCode(String hisPartCode) {
		this.hisPartCode = hisPartCode;
	}

	public String getHisApparatusCode() {
		return hisApparatusCode;
	}

	public void setHisApparatusCode(String hisApparatusCode) {
		this.hisApparatusCode = hisApparatusCode;
	}

	public String getStructuringName() {
		return structuringName;
	}

	public void setStructuringName(String structuringName) {
		this.structuringName = structuringName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
