package com.lantone.icss.trans.langtong.model.response.at;

import java.io.Serializable;
import java.util.List;
import com.google.common.collect.Lists;

public class ExamineInfoWrapper implements Serializable{

	private static final long serialVersionUID = 1L;
	private String ID;
	//病人姓名
	private String patName;
	//就诊序号
	private String visId;
	//开单时间
	private String exaDate;
	//开单科室
	private String depId;
	//开单医生
	private String sffId;
	//单据类型（1检验，0检查，2诊疗单）
	private String exaType;
	//所属机构
	private String hospitalId;
	//病人序号
	private String patId;
	//检查时间
	private String examineExaDate;
	//检查摘要
	private String examineSummary;
	//检查检验诊疗单明细
	private List<ListExamineDetailWrapper> examineDetailWrapperList=Lists.newArrayList();
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getVisId() {
		return visId;
	}
	public void setVisId(String visId) {
		this.visId = visId;
	}
	public String getExaDate() {
		return exaDate;
	}
	public void setExaDate(String exaDate) {
		this.exaDate = exaDate;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getSffId() {
		return sffId;
	}
	public void setSffId(String sffId) {
		this.sffId = sffId;
	}
	public String getExaType() {
		return exaType;
	}
	public void setExaType(String exaType) {
		this.exaType = exaType;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getPatId() {
		return patId;
	}
	public void setPatId(String patId) {
		this.patId = patId;
	}
	public String getExamineExaDate() {
		return examineExaDate;
	}
	public void setExamineExaDate(String examineExaDate) {
		this.examineExaDate = examineExaDate;
	}
	public String getExamineSummary() {
		return examineSummary;
	}
	public void setExamineSummary(String examineSummary) {
		this.examineSummary = examineSummary;
	}
	public List<ListExamineDetailWrapper> getExamineDetailWrapperList() {
		return examineDetailWrapperList;
	}
	public void setExamineDetailWrapperList(List<ListExamineDetailWrapper> examineDetailWrapperList) {
		this.examineDetailWrapperList = examineDetailWrapperList;
	}
}
