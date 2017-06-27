package com.lantone.icss.api.his.model;

import java.io.Serializable;

public class HisDiseaseInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String hisDiseaseCode;    //his疾病编码
	private String hospitalCode;      //医院编码
	private String parentCode;        //上级编码
	private String code;              //icd10编码
	private String name;              //名称
	private String shortName;         //简称
	private String spell;             //拼音
	private String fiveStroke;        //五笔
	private String status;            //状态（1：有效，0：无效）
	private String remark;            //备注
	
	public String getHisDiseaseCode() {
		return hisDiseaseCode;
	}
	public void setHisDiseaseCode(String hisDiseaseCode) {
		this.hisDiseaseCode = hisDiseaseCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getSpell() {
		return spell;
	}
	public void setSpell(String spell) {
		this.spell = spell;
	}
	public String getFiveStroke() {
		return fiveStroke;
	}
	public void setFiveStroke(String fiveStroke) {
		this.fiveStroke = fiveStroke;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
