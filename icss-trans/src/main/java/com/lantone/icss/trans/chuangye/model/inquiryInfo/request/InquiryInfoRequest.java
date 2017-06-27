package com.lantone.icss.trans.chuangye.model.inquiryInfo.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "BODY")
public class InquiryInfoRequest {

	@XmlElement(name = "PATIENTID")
	private String patientId;	//病人流水号
	@XmlElement(name = "CHIEFCOMPLAINT")
	private String chiefComplaint;	//主诉
	@XmlElement(name = "PRESENTHISTORY")
	private String presentHistory;	//现病史
	@XmlElement(name = "PASTHISTORY")
	private String pastHistory;	//既往史
	@XmlElement(name = "OTHERHISTORY")
	private String otherHistory;	//其他史
	@XmlElement(name = "SIGNS")
	private String signs;	//体征
	@XmlElement(name = "ASSAY")
	private String assay;	//化验
	@XmlElement(name = "SITEADVISOR")
	private String siteAdvisor;	//器查
	@XmlElement(name = "DIAGNOSIS")
	private String diagnosis;	//诊断
	@XmlElement(name = "TREATMENT")
	private String treatment;	//治疗
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getChiefComplaint() {
		return chiefComplaint;
	}
	public void setChiefComplaint(String chiefComplaint) {
		this.chiefComplaint = chiefComplaint;
	}
	public String getPresentHistory() {
		return presentHistory;
	}
	public void setPresentHistory(String presentHistory) {
		this.presentHistory = presentHistory;
	}
	public String getPastHistory() {
		return pastHistory;
	}
	public void setPastHistory(String pastHistory) {
		this.pastHistory = pastHistory;
	}
	public String getOtherHistory() {
		return otherHistory;
	}
	public void setOtherHistory(String otherHistory) {
		this.otherHistory = otherHistory;
	}
	public String getSigns() {
		return signs;
	}
	public void setSigns(String signs) {
		this.signs = signs;
	}
	public String getAssay() {
		return assay;
	}
	public void setAssay(String assay) {
		this.assay = assay;
	}
	public String getSiteAdvisor() {
		return siteAdvisor;
	}
	public void setSiteAdvisor(String siteAdvisor) {
		this.siteAdvisor = siteAdvisor;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	
}
