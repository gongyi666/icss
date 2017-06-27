package com.lantone.icss.api.kl.model;

import java.io.Serializable;

@SuppressWarnings("serial") 
public class GroupDrugDetail implements Serializable {
	private Long id;
	private Long drgId;
	private String drugName;
	private int grpNum;
	private String speId;
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	private String manId;
	private java.math.BigDecimal drgOnceDose;
	private String drgDoseUnit;
	private String modId;
	private String freEnName;
	private int drgUseDay;
	private int drgGroupNum;
	private int drgQuantity;
	private String hisDrgName;
	private Long doctorId;
	private String hospitalCode;
	public Long getDrgGroupId() {
		return drgGroupId;
	}
	public void setDrgGroupId(Long drgGroupId) {
		this.drgGroupId = drgGroupId;
	}
	private String skinTest;
	private Long drgGroupId;
    public String getSkinTest() {
		return skinTest;
	}
	public void setSkinTest(String skinTest) {
		this.skinTest = skinTest;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDrgId() {
		return drgId;
	}
	public void setDrgId(Long drgId) {
		this.drgId = drgId;
	}
	public int getGrpNum() {
		return grpNum;
	}
	public void setGrpNum(int grpNum) {
		this.grpNum = grpNum;
	}
	public String getSpeId() {
		return speId;
	}
	public void setSpeId(String speId) {
		this.speId = speId;
	}
	public String getManId() {
		return manId;
	}
	public void setManId(String manId) {
		this.manId = manId;
	}
	public java.math.BigDecimal getDrgOnceDose() {
		return drgOnceDose;
	}
	public void setDrgOnceDose(java.math.BigDecimal drgOnceDose) {
		this.drgOnceDose = drgOnceDose;
	}
	public String getDrgDoseUnit() {
		return drgDoseUnit;
	}
	public void setDrgDoseUnit(String drgDoseUnit) {
		this.drgDoseUnit = drgDoseUnit;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getFreEnName() {
		return freEnName;
	}
	public void setFreEnName(String freEnName) {
		this.freEnName = freEnName;
	}
	public int getDrgUseDay() {
		return drgUseDay;
	}
	public void setDrgUseDay(int drgUseDay) {
		this.drgUseDay = drgUseDay;
	}
	public int getDrgGroupNum() {
		return drgGroupNum;
	}
	public void setDrgGroupNum(int drgGroupNum) {
		this.drgGroupNum = drgGroupNum;
	}
	public int getDrgQuantity() {
		return drgQuantity;
	}
	public void setDrgQuantity(int drgQuantity) {
		this.drgQuantity = drgQuantity;
	}
	public String getHisDrgName() {
		return hisDrgName;
	}
	public void setHisDrgName(String hisDrgName) {
		this.hisDrgName = hisDrgName;
	}
	public String getHisDrgId() {
		return HisDrgId;
	}
	public void setHisDrgId(String hisDrgId) {
		HisDrgId = hisDrgId;
	}
	private String HisDrgId;
}
