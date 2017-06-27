package com.lantone.icss.api.at.model;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.DiseaseInfo;

/**
 * @Description:返回HIS的对象
 * @author : luwg
 * @time : 2016年10月12日 下午1:34:38
 * 
 */
public class PostHisInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	//初步诊断结果
	private List<DiseaseInfo> diseaseList = Lists.newArrayList();
	
//	//初步诊断结果
//	private List<HisDiseaseInfo> hisDiseaseList = Lists.newArrayList();
	//问诊记录
	private RecordInfo recordInfo;
	//患者信息
	private PatientInfo patientInfo;
	//主诉
	private String chiefDesc;
	//现病史
	private String presentDesc;
	//体格检查
	private String physicalDesc;
	//既往史
	private String historyDesc;
	//婚育史
	private String marriageDesc;
	//过敏史
	private String allergyDesc;
	//家族史
	private String familyDesc;
	//个人史
	private String personDesc;
	//处理意见
	private String opinionDesc;
	
	public List<DiseaseInfo> getDiseaseList() {
		return diseaseList;
	}
	public void setDiseaseList(List<DiseaseInfo> diseaseList) {
		this.diseaseList = diseaseList;
	}
	public PatientInfo getPatientInfo() {
		return patientInfo;
	}
	public void setPatientInfo(PatientInfo patientInfo) {
		this.patientInfo = patientInfo;
	}
	public String getChiefDesc() {
		return chiefDesc;
	}
	public void setChiefDesc(String chiefDesc) {
		this.chiefDesc = chiefDesc;
	}
	public String getPresentDesc() {
		return presentDesc;
	}
	public void setPresentDesc(String presentDesc) {
		this.presentDesc = presentDesc;
	}
	public String getPhysicalDesc() {
		return physicalDesc;
	}
	public void setPhysicalDesc(String physicalDesc) {
		this.physicalDesc = physicalDesc;
	}
	public String getHistoryDesc() {
		return historyDesc;
	}
	public void setHistoryDesc(String historyDesc) {
		this.historyDesc = historyDesc;
	}
	public String getMarriageDesc() {
		return marriageDesc;
	}
	public void setMarriageDesc(String marriageDesc) {
		this.marriageDesc = marriageDesc;
	}
	public String getAllergyDesc() {
		return allergyDesc;
	}
	public void setAllergyDesc(String allergyDesc) {
		this.allergyDesc = allergyDesc;
	}
	public String getFamilyDesc() {
		return familyDesc;
	}
	public void setFamilyDesc(String familyDesc) {
		this.familyDesc = familyDesc;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPersonDesc() {
		return personDesc;
	}
	public void setPersonDesc(String personDesc) {
		this.personDesc = personDesc;
	}
	public String getOpinionDesc() {
		return opinionDesc;
	}
	public void setOpinionDesc(String opinionDesc) {
		this.opinionDesc = opinionDesc;
	}
	public RecordInfo getRecordInfo() {
		return recordInfo;
	}
	public void setRecordInfo(RecordInfo recordInfo) {
		this.recordInfo = recordInfo;
	}
	
	
}
