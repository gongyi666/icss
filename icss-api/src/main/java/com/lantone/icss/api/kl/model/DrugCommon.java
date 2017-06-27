package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class DrugCommon implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long drgId;

	private String speId;
	private String manId;
	private java.math.BigDecimal drgOnceDose;
	private String drgDoseUnit;
	private String modId;
	private String freEnName;
	private int drgUseDay;
	private Long doctorId;
	private String hospitalCode;
	private int drgQuantity;

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

	

	public int getDrgQuantity() {
		return drgQuantity;
	}

	public void setDrgQuantity(int drgQuantity) {
		this.drgQuantity = drgQuantity;
	}
}
