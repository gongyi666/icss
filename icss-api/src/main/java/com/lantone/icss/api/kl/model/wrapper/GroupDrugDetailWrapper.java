package com.lantone.icss.api.kl.model.wrapper;

import java.math.BigDecimal;
import java.util.List;

import  com.lantone.icss.api.his.model.HisDrugInfoDetail;
import com.lantone.icss.api.kl.model.GroupDrugDetail;
@SuppressWarnings("serial")
public class GroupDrugDetailWrapper extends GroupDrugDetail {
	
  	private List<HisDrugInfoDetail> hisDrugInfoDetail;
	
	public List<HisDrugInfoDetail> getHisDrugInfoDetail() {
		return hisDrugInfoDetail;
	}
	
	public void setHisDrugInfoDetail(List<HisDrugInfoDetail> hisDrugInfoDetail) {
		this.hisDrugInfoDetail = hisDrugInfoDetail;
	}
	private int crowdType;
	private String type;
	private String isGroup;
	private String drugPsychotropic;
	private String drugFormulation;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(String isGroup) {
		this.isGroup = isGroup;
	}

	public String getDrugPsychotropic() {
		return drugPsychotropic;
	}

	public void setDrugPsychotropic(String drugPsychotropic) {
		this.drugPsychotropic = drugPsychotropic;
	}

	public String getDrugFormulation() {
		return drugFormulation;
	}

	public void setDrugFormulation(String drugFormulation) {
		this.drugFormulation = drugFormulation;
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
	private java.math.BigDecimal minDosage;
	private java.math.BigDecimal maxDosage;
	private java.math.BigDecimal commonDosage;
	
	private Integer drugCourse; //疗程
	private java.math.BigDecimal maxDayDosage;  //每日最大剂量
	private java.math.BigDecimal minDayDosage;  //每日最小剂量

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
	
}
