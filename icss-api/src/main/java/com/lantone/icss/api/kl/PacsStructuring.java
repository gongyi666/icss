package com.lantone.icss.api.kl;

import java.io.Serializable;

public class PacsStructuring implements Serializable{

	private static final long serialVersionUID = 1L;

	private String apparatusCode;     //器械
	private String partCode;          //部位（器官）
	private String model;             //机型
	private String requirementCode;   //要求
	private String direction;         //方向
	private String otherCode;         //其他
	private String position;          //体位
	private String hospitalCode;      //医院编码
	
	
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getRequirementCode() {
		return requirementCode;
	}
	public void setRequirementCode(String requirementCode) {
		this.requirementCode = requirementCode;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getOtherCode() {
		return otherCode;
	}
	public void setOtherCode(String otherCode) {
		this.otherCode = otherCode;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

}
