package com.lantone.icss.trans.langtong.model.response.at;

import java.io.Serializable;

public class HISDeptInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;	//id
	private String	depName;	//科室名称
	private String	depCode;	//科室编码
	private String	depAlias;	//科室别名
	private String	depFatherId;	//父类科室代码
	private String	depUpstage;	//末级判别	
	private String	depLevel;	//级次
	private String	chinaSpell;	//拼音码
	private String	fiveStroke;	//五笔码
	private String	depCategory;	//科室类别
	private String	depRegistration;	//挂号判别
	private String	depHospitalization;	//住院判别
	private String	depClinic;	//门诊判别
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getDepCode() {
		return depCode;
	}
	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}
	public String getDepAlias() {
		return depAlias;
	}
	public void setDepAlias(String depAlias) {
		this.depAlias = depAlias;
	}
	
	public String getDepFatherId() {
		return depFatherId;
	}
	public void setDepFatherId(String depFatherId) {
		this.depFatherId = depFatherId;
	}
	public String getDepUpstage() {
		return depUpstage;
	}
	public void setDepUpstage(String depUpstage) {
		this.depUpstage = depUpstage;
	}
	public String getDepLevel() {
		return depLevel;
	}
	public void setDepLevel(String depLevel) {
		this.depLevel = depLevel;
	}
	public String getChinaSpell() {
		return chinaSpell;
	}
	public void setChinaSpell(String chinaSpell) {
		this.chinaSpell = chinaSpell;
	}
	public String getFiveStroke() {
		return fiveStroke;
	}
	public void setFiveStroke(String fiveStroke) {
		this.fiveStroke = fiveStroke;
	}
	public String getDepCategory() {
		return depCategory;
	}
	public void setDepCategory(String depCategory) {
		this.depCategory = depCategory;
	}
	public String getDepRegistration() {
		return depRegistration;
	}
	public void setDepRegistration(String depRegistration) {
		this.depRegistration = depRegistration;
	}
	public String getDepHospitalization() {
		return depHospitalization;
	}
	public void setDepHospitalization(String depHospitalization) {
		this.depHospitalization = depHospitalization;
	}
	public String getDepClinic() {
		return depClinic;
	}
	public void setDepClinic(String depClinic) {
		this.depClinic = depClinic;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
