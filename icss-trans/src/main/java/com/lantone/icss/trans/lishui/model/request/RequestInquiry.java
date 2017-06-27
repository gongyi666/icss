package com.lantone.icss.trans.lishui.model.request;

import java.io.Serializable;

public class RequestInquiry implements Serializable {
	private static final long serialVersionUID = 1L;

	private String patientId;	//患者ID
	private String patName;	//姓名
	private String ChiefComplaint;	//主诉
	private String PresentHistory;	//现病史
	private String PastHistory;	//既往史
	private String OtherHistory;	//其他史
	private String Signs;	//体征
	private String Assay;	//化验
	private String SiteAdvisor; //器查
	private String Diagnosis;	//诊断
	private String Treatment;	//治疗
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getChiefComplaint() {
		return ChiefComplaint;
	}
	public void setChiefComplaint(String chiefComplaint) {
		ChiefComplaint = chiefComplaint;
	}
	public String getPresentHistory() {
		return PresentHistory;
	}
	public void setPresentHistory(String presentHistory) {
		PresentHistory = presentHistory;
	}
	public String getPastHistory() {
		return PastHistory;
	}
	public void setPastHistory(String pastHistory) {
		PastHistory = pastHistory;
	}
	public String getOtherHistory() {
		return OtherHistory;
	}
	public void setOtherHistory(String otherHistory) {
		OtherHistory = otherHistory;
	}
	public String getSigns() {
		return Signs;
	}
	public void setSigns(String signs) {
		Signs = signs;
	}
	public String getAssay() {
		return Assay;
	}
	public void setAssay(String assay) {
		Assay = assay;
	}
	public String getSiteAdvisor() {
		return SiteAdvisor;
	}
	public void setSiteAdvisor(String siteAdvisor) {
		SiteAdvisor = siteAdvisor;
	}
	public String getDiagnosis() {
		return Diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		Diagnosis = diagnosis;
	}
	public String getTreatment() {
		return Treatment;
	}
	public void setTreatment(String treatment) {
		Treatment = treatment;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
