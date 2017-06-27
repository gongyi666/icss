package com.lantone.icss.api.at.model.wrapper;

import java.util.Date;
import java.util.List;

import com.lantone.icss.api.at.model.InquiryInfo;
import com.lantone.icss.api.at.model.PatientInfo;

public class InquiryInfoWrapper extends InquiryInfo{

	private static final long serialVersionUID = 1L;

	private List<InquiryDetailWrapper> details;
	private List<InquiryLogWrapper> logs;
	
	private String detailStr;	//明细信息
	private Integer detailFlag; //1:同时查询明细
	private Long diseaseType;   //疾病分类
	private Date startTime;		//参数：开始日期
	private Date endTime;		//参数：结束日期
	private String deptOrDiagnose;//参数：科室或疾病
	private String name;          //diagnose对应的name，跟前端对应
	
	private String hisId;		  	//从his返回的id
	private String toPat; 		  	//病人基础信息
	private String toHistory;		//病历信息
	private String toDiagnose;      //疾病诊断
	private String toRequest;		//检验检查
	private String toRequestDetail;	//检验检查明细
	private String toPreindex;		//预检字典
	private String toPrescript;		//处方信息
	private String toPrescriptXy;	//中药明细
	private String toPrescriptZcy;	//中成药明细
	private String toPrescriptCy;	//草药明细
	private String toLisDetail;		//化验明细
	private String toPacsDetail; 	//器查明细


	
	
	
	public List<InquiryLogWrapper> getLogs() {
		return logs;
	}

	public void setLogs(List<InquiryLogWrapper> logs) {
		this.logs = logs;
	}

	public String getToPrescriptXy() {
		return toPrescriptXy;
	}

	public void setToPrescriptXy(String toPrescriptXy) {
		this.toPrescriptXy = toPrescriptXy;
	}

	public String getToPrescriptZcy() {
		return toPrescriptZcy;
	}

	public void setToPrescriptZcy(String toPrescriptZcy) {
		this.toPrescriptZcy = toPrescriptZcy;
	}

	public String getToPrescriptCy() {
		return toPrescriptCy;
	}

	public void setToPrescriptCy(String toPrescriptCy) {
		this.toPrescriptCy = toPrescriptCy;
	}

	public String getToLisDetail() {
		return toLisDetail;
	}

	public void setToLisDetail(String toLisDetail) {
		this.toLisDetail = toLisDetail;
	}

	public String getToPacsDetail() {
		return toPacsDetail;
	}

	public void setToPacsDetail(String toPacsDetail) {
		this.toPacsDetail = toPacsDetail;
	}

	public String getToPat() {
		return toPat;
	}

	public void setToPat(String toPat) {
		this.toPat = toPat;
	}

	public String getToHistory() {
		return toHistory;
	}

	public void setToHistory(String toHistory) {
		this.toHistory = toHistory;
	}

	public String getToDiagnose() {
		return toDiagnose;
	}

	public void setToDiagnose(String toDiagnose) {
		this.toDiagnose = toDiagnose;
	}

	public String getToRequest() {
		return toRequest;
	}

	public void setToRequest(String toRequest) {
		this.toRequest = toRequest;
	}

	public String getToRequestDetail() {
		return toRequestDetail;
	}

	public void setToRequestDetail(String toRequestDetail) {
		this.toRequestDetail = toRequestDetail;
	}

	public String getToPreindex() {
		return toPreindex;
	}

	public void setToPreindex(String toPreindex) {
		this.toPreindex = toPreindex;
	}

	public String getToPrescript() {
		return toPrescript;
	}

	public void setToPrescript(String toPrescript) {
		this.toPrescript = toPrescript;
	}

	public String getHisId() {
		return hisId;
	}

	public void setHisId(String hisId) {
		this.hisId = hisId;
	}




	public String getDetailStr() {
		return detailStr;
	}

	public void setDetailStr(String detailStr) {
		this.detailStr = detailStr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeptOrDiagnose() {
		return deptOrDiagnose;
	}

	public void setDeptOrDiagnose(String deptOrDiagnose) {
		this.deptOrDiagnose = deptOrDiagnose;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getDiseaseType() {
		return diseaseType;
	}

	public void setDiseaseType(Long diseaseType) {
		this.diseaseType = diseaseType;
	}

	public Integer getDetailFlag() {
		return detailFlag;
	}

	public void setDetailFlag(Integer detailFlag) {
		this.detailFlag = detailFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<InquiryDetailWrapper> getDetails() {
		return details;
	}

	public void setDetails(List<InquiryDetailWrapper> details) {
		this.details = details;
	}
	
	
}
