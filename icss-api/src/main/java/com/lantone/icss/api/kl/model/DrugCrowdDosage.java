package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class DrugCrowdDosage implements Serializable {
	private static final long serialVersionUID = 1L;
	/*
	 * id drug_id crowd_type min_dosage max_dosage common_dosage dosage_type
	 * drug_usage drug_frequency disease_id
	 */
	private Long id;
	private Long drugId;
	private int crowdType;
	private java.math.BigDecimal minDosage;
	private String crowdSex;
	private int crowdStarAge;
	private int crowdEndAge;
	private int age;
	private String dosageUnit;
	private Integer drugCourse;   //疗程
	private java.math.BigDecimal maxDayDosage;  //每日最大剂量
	private java.math.BigDecimal minDayDosage;  //每日最小剂量
	
	public String getDosageUnit() {
		return dosageUnit;
	}
	public void setDosageUnit(String dosageUnit) {
		this.dosageUnit = dosageUnit;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCrowdSex() {
		return crowdSex;
	}
	public void setCrowdSex(String crowdSex) {
		this.crowdSex = crowdSex;
	}
	public int getCrowdStarAge() {
		return crowdStarAge;
	}
	public void setCrowdStarAge(int crowdStarAge) {
		this.crowdStarAge = crowdStarAge;
	}
	public int getCrowdEndAge() {
		return crowdEndAge;
	}
	public void setCrowdEndAge(int crowdEndAge) {
		this.crowdEndAge = crowdEndAge;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDrugId() {
		return drugId;
	}
	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}
	public int getCrowdType() {
		return crowdType;
	}
	public void setCrowdType(int crowdType) {
		this.crowdType = crowdType;
	}
	public java.math.BigDecimal getMinDosage() {
		return minDosage;
	}
	public void setMinDosage(java.math.BigDecimal minDosage) {
		this.minDosage = minDosage;
	}
	public java.math.BigDecimal getMaxDosage() {
		return maxDosage;
	}
	public void setMaxDosage(java.math.BigDecimal maxDosage) {
		this.maxDosage = maxDosage;
	}
	public java.math.BigDecimal getCommonDosage() {
		return commonDosage;
	}
	public void setCommonDosage(java.math.BigDecimal commonDosage) {
		this.commonDosage = commonDosage;
	}
	public int getDosageType() {
		return dosageType;
	}
	public void setDosageType(int dosageType) {
		this.dosageType = dosageType;
	}
	public String getDrugUsage() {
		return drugUsage;
	}
	public void setDrugUsage(String drugUsage) {
		this.drugUsage = drugUsage;
	}
	public String getDrugFrequency() {
		return drugFrequency;
	}
	public void setDrugFrequency(String drugFrequency) {
		this.drugFrequency = drugFrequency;
	}
	public Long getDiseaseId() {
		return diseaseId;
	}
	public void setDiseaseId(Long diseaseId) {
		this.diseaseId = diseaseId;
	}
	public Integer getDrugCourse() {
		return drugCourse;
	}
	public void setDrugCourse(Integer drugCourse) {
		this.drugCourse = drugCourse;
	}
	public java.math.BigDecimal getMaxDayDosage() {
		return maxDayDosage;
	}
	public void setMaxDayDosage(java.math.BigDecimal maxDayDosage) {
		this.maxDayDosage = maxDayDosage;
	}
	public java.math.BigDecimal getMinDayDosage() {
		return minDayDosage;
	}
	public void setMinDayDosage(java.math.BigDecimal minDayDosage) {
		this.minDayDosage = minDayDosage;
	}


	private java.math.BigDecimal maxDosage;
	private java.math.BigDecimal commonDosage;
	private int dosageType;
	private String drugUsage;
	private String drugFrequency;
	private Long diseaseId;
}
