package com.lantone.icss.trans.langtong.model.response.kl;

import java.io.Serializable;

public class HISPubDiseaseInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;	//id
	private String disName;	//疾病名称
	private String disIcd;	//ICD
	private String chinaSpell;	//拼音输入码
	private String fiveStroke;	//五笔输入码
	private String disType;	//疾病类型
	private String disSummary;	//疾病描述
	private String disIntroduce;	//疾病介绍
	private String disTypicalSymptom;	//典型症状
	private String disClinicalExamination;	//临床检查
	private String disDiagCriteria;	//诊断标准
	private String disTheraMethod;	//治疗方法
	private String disComplication;	//并发症
	private String disState;	//状态

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDisName() {
		return disName;
	}
	public void setDisName(String disName) {
		this.disName = disName;
	}
	public String getDisIcd() {
		return disIcd;
	}
	public void setDisIcd(String disIcd) {
		this.disIcd = disIcd;
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
	public String getDisType() {
		return disType;
	}
	public void setDisType(String disType) {
		this.disType = disType;
	}
	public String getDisSummary() {
		return disSummary;
	}
	public void setDisSummary(String disSummary) {
		this.disSummary = disSummary;
	}
	public String getDisIntroduce() {
		return disIntroduce;
	}
	public void setDisIntroduce(String disIntroduce) {
		this.disIntroduce = disIntroduce;
	}
	public String getDisTypicalSymptom() {
		return disTypicalSymptom;
	}
	public void setDisTypicalSymptom(String disTypicalSymptom) {
		this.disTypicalSymptom = disTypicalSymptom;
	}
	public String getDisClinicalExamination() {
		return disClinicalExamination;
	}
	public void setDisClinicalExamination(String disClinicalExamination) {
		this.disClinicalExamination = disClinicalExamination;
	}
	public String getDisDiagCriteria() {
		return disDiagCriteria;
	}
	public void setDisDiagCriteria(String disDiagCriteria) {
		this.disDiagCriteria = disDiagCriteria;
	}
	public String getDisTheraMethod() {
		return disTheraMethod;
	}
	public void setDisTheraMethod(String disTheraMethod) {
		this.disTheraMethod = disTheraMethod;
	}
	public String getDisComplication() {
		return disComplication;
	}
	public void setDisComplication(String disComplication) {
		this.disComplication = disComplication;
	}
	public String getDisState() {
		return disState;
	}
	public void setDisState(String disState) {
		this.disState = disState;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
