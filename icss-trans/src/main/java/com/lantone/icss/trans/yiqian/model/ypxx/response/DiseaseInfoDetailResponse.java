package com.lantone.icss.trans.yiqian.model.ypxx.response;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *	Description: 药品频率信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月11日上午11:00:42
 */
@XmlRootElement(name ="row")
public class DiseaseInfoDetailResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	//@XmlElement(name = "id")
	private String id = "";	//
	//@XmlElement(name = "disName")
	private String disName = "";	//疾病名称
	//@XmlElement(name = "disIcd")
	private String disIcd = "";	//ICD
	//@XmlElement(name = "chinaSpell")
	private String chinaSpell = "";	//拼音输入码
	//@XmlElement(name = "fiveStroke")
	private String fiveStroke = "";	//五笔输入码
	//@XmlElement(name = "disType")
	private String disType = "";	//疾病类型
	//@XmlElement(name = "disSummary")
	private String disSummary = "";	//疾病描述
	//@XmlElement(name = "disIntroduce")
	private String disIntroduce = "";	//疾病介绍
	//@XmlElement(name = "disTypicalSymptom")
	private String disTypicalSymptom = "";	//典型症状
	//@XmlElement(name = "disClinicalExamination")
	private String disClinicalExamination = "";	//临床检查
	//@XmlElement(name = "disDiagCriteria")
	private String disDiagCriteria = "";	//诊断标准
	//@XmlElement(name = "disTheraMethod")
	private String disTheraMethod = "";	//治疗方法
	//@XmlElement(name = "disComplication")
	private String disComplication = "";	//并发症
	//@XmlElement(name = "disState")
	private String disState = "";	//状态
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
	
}
